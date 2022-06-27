package com.lhw.mongo.service;

import com.lhw.mongo.contants.BaseConstants;
import com.lhw.mongo.exception.MongoHandlerException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.Cleanup;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author ：linhw
 * @date ：22.6.22 17:26
 * @description：mongo处理服务
 * @modified By：
 */
@Service
public class PrivateMongoServiceImpl implements IPrivateMongoService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFsBucket;

    @Override
    public Map<String, String> getFileNameByFileIdIn(List<String> fileIds) {
        GridFSFindIterable iterable = gridFsTemplate.find(new Query(
                Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_ID)
                        .in(fileIds)));
        final Map<String, String> result = new HashMap<>(16);
        if (null != iterable.first()) {
            iterable.forEach((Consumer<? super GridFSFile>) gridFsFile ->
                    result.putIfAbsent(gridFsFile.getObjectId().toString(), gridFsFile.getFilename()));
        }
        return result;
    }

    /**
     * 在传入的文件ID中，查询并返回存在于Mongo库中的文件名
     *
     * @param fileIds
     * @return
     */
    @Override
    public Map<String, List<String>> listFileNameByFileIdInAndGroupByType(List<String> fileIds) {
        GridFSFindIterable iterable = gridFsTemplate.find(new Query(
                Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_ID)
                        .in(fileIds)));
        final Map<String, List<String>> result = new HashMap<>(16);
        if (null != iterable.first()) {
            iterable.forEach((Consumer<? super GridFSFile>) gridFsFile -> {
                Document metadata = gridFsFile.getMetadata();
                if (null != metadata) {
                    String type = metadata.getString(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_TYPE);
                    if (!StringUtils.isEmpty(type)) {
                        if (!result.containsKey(type)) {
                            List<String> temp = Collections.singletonList(gridFsFile.getFilename());
                            result.putIfAbsent(type, temp);
                        } else {
                            result.get(type).add(gridFsFile.getFilename());
                        }
                    }
                }
            });
        }
        return result;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile, String objectCode, String type) {
        String fileName = multipartFile.getOriginalFilename();
        String[] strArray = Optional.ofNullable(fileName).orElse("").split("\\.");
        String fileSuffix = strArray[strArray.length - 1];
        try {
            @Cleanup InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
            return uploadFileByInputStream(inputStream, fileName, objectCode, type, fileSuffix);
        } catch (IOException e) {
            throw new MongoHandlerException.FileUploadFail(fileName);
        }
    }

    @Override
    public String uploadFileByInputStream(InputStream inputStream, String fileName, String objectCode, String type, String fileSuffix) {
        //如果objectCode不为空且该二维码已存在，就删掉重新生成
        if (!StringUtils.isEmpty(objectCode) && checkFileExistByObjectCode(objectCode)) {
            deleteFileByCode(objectCode);
        }
        try {
            @Cleanup InputStream tempInputStream = inputStream;
            BasicDBObject metadata = new BasicDBObject(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_NAME, fileName);
            metadata.append(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_SUFFIX, fileSuffix);
            metadata.append(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_TYPE, type);
            if (!StringUtils.isEmpty(objectCode)) {
                metadata.append(BaseConstants.FileConstants.MongoGridFsMetadata.OBJECT_CODE, objectCode);
            }
            ObjectId objectId = gridFsTemplate.store(tempInputStream, fileName, fileSuffix, metadata);
            return objectId.toString();
        } catch (Exception e) {
            throw new MongoHandlerException.FileUploadFail(fileName);
        }
    }

    @Override
    public void deleteFileByFileId(String fileId) {
        Query query = new Query(Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_ID).is(fileId));
        gridFsTemplate.delete(query);
    }

    @Override
    public void deleteFileByCode(String objectCode) {
        Query query = new Query(Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.META_DATA_PREFIX +
                BaseConstants.FileConstants.MongoGridFsMetadata.OBJECT_CODE).is(objectCode));
        gridFsTemplate.delete(query);
    }

    @Override
    public String getFileNameByFileId(String fileId) {
        Query query = new Query(Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_ID).is(fileId));
        GridFSFile gridFsFile = Optional.ofNullable(gridFsTemplate.findOne(query))
                .orElseThrow(() -> new MongoHandlerException.FileNoExist(fileId));
        Document document = gridFsFile.getMetadata();
        if (null != document) {
            return Optional.ofNullable(document.getString(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_NAME))
                    .orElseThrow(() -> new MongoHandlerException.FileNameNotDefinition(fileId));
        }
        return null;
    }

    @Override
    public InputStream getDownloadFileStreamByFileId(String fileId) throws Exception {
        Query query = new Query(Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.FILE_ID).is(fileId));
        GridFSFile gridFsDbFile = Optional.ofNullable(gridFsTemplate.findOne(query))
                .orElseThrow(() -> new MongoHandlerException.FileDownloadFail(fileId));
        //打开流对象
        GridFSDownloadStream gridFSDownloadStream = gridFsBucket.openDownloadStream(gridFsDbFile.getObjectId());
        //获取流对象
        GridFsResource resource = new GridFsResource(gridFsDbFile, gridFSDownloadStream);
        //获取数据
        return resource.getInputStream();
    }

    @Override
    public InputStream getDownloadFileStreamByObjectCode(String objectCode) throws Exception {
        Query query = new Query(Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.META_DATA_PREFIX +
                BaseConstants.FileConstants.MongoGridFsMetadata.OBJECT_CODE).is(objectCode));
        GridFSFile gridFsDbFile = Optional.ofNullable(gridFsTemplate.findOne(query))
                .orElseThrow(() -> new MongoHandlerException.FileNoExist(objectCode));
        //打开流对象
        GridFSDownloadStream gridFsDownloadStream = gridFsBucket.openDownloadStream(gridFsDbFile.getObjectId());
        //获取流对象
        GridFsResource resource = new GridFsResource(gridFsDbFile, gridFsDownloadStream);
        //获取数据
        return resource.getInputStream();
    }

    private boolean checkFileExistByObjectCode(String objectCode) {
        Query query = new Query(Criteria.where(BaseConstants.FileConstants.MongoGridFsMetadata.META_DATA_PREFIX +
                BaseConstants.FileConstants.MongoGridFsMetadata.OBJECT_CODE).is(objectCode));
        GridFSFile gridFsFile = gridFsTemplate.findOne(query);
        if (null != gridFsFile) {
            return true;
        } else {
            return false;
        }
    }


}
