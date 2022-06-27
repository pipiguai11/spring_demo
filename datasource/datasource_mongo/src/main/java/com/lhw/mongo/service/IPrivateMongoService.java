package com.lhw.mongo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author ：linhw
 * @date ：22.6.22 17:25
 * @description：mongo处理服务
 * @modified By：
 */
public interface IPrivateMongoService {

    Map<String, String> getFileNameByFileIdIn(List<String> fileIds);

    Map<String, List<String>> listFileNameByFileIdInAndGroupByType(List<String> fileIds);

    String uploadFile(MultipartFile multipartFile, String objectCode, String type);

    String uploadFileByInputStream(InputStream inputStream, String fileName, String objectCode, String type, String fileSuffix);

    void deleteFileByFileId(String fileId);

    void deleteFileByCode(String objectCode);

    String getFileNameByFileId(String fileId);

    InputStream getDownloadFileStreamByFileId(String fileId) throws Exception;

    InputStream getDownloadFileStreamByObjectCode(String objectCode) throws Exception;

}
