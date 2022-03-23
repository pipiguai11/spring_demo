package com.lhw.mongo;

import com.lhw.mongo.model.Person;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.*;
import java.util.List;

@SpringBootTest
class DatasourceMongoApplicationTests {

//    private static final Logger log = LoggerFactory.getLogger(DatasourceMongoApplicationTests.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    /**
     * 保存集合信息
     */
    @Test
    public void test(){
        Person p = new Person("asd",14);
        mongoTemplate.save(p);
        System.out.println("success");
    }

    /**
     * 文件上传到存储桶
     * @throws FileNotFoundException
     */
    @Test
    public void test2() throws FileNotFoundException {
        File f = new File("E:\\日常文件\\数慧\\12数据库设计说明书(模板).doc");
        if(!f.exists()){
            System.out.println("文件不存在");
        }
        String fileName = f.getName();
        FileInputStream fis = new FileInputStream(f);
        ObjectId objectId = gridFsTemplate.store(fis,fileName,"");
        System.out.println(objectId.toString());
    }

    /**
     * 从存储桶中下载文件
     */
    @Test
    public void testConnect(){

//        log.info("getting file by _id");
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(GridFsCriteria.where("_id").is("623a93f12cd0633862a8c379")));
        //GridFSFile gridFSFile = gridFsTemplate.findOne(q);
        //log.info("getting fin + " +gridFSFile.getObjectId() + gridFSFile.getFilename());
        System.out.println(gridFSFile.getFilename());
        System.out.println(gridFSFile.getObjectId());
//        log.info("starting download");

        String fileString = "E:\\temp\\" + gridFSFile.getFilename();

        try{
            //打开流下载对象
            GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //获取流对象
            GridFsResource gfr = new GridFsResource(gridFSFile,downloadStream);
            //获取数据流
            InputStream inputStream = gfr.getInputStream();
            //基于上面这个数据流做操作，可以下载等
            OutputStream out = new FileOutputStream(fileString);
            IOUtils.copy(inputStream,out);
            //fileString = IOUtils.toString(gfr.getInputStream(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(fileString);

    }

    /**
     * 通过Criteria对象查询需要的信息
     */
    @Test
    public void getInfo1(){
        Query query = new Query(Criteria.where("name").is("ss"));
        Query query1 = new Query(Criteria.where("age").gte(1));  //获取年龄大于等于1的所有Person信息
        Person person = mongoTemplate.findOne(query,Person.class);
        List<Person> list =  mongoTemplate.find(query1,Person.class);
        System.out.println(person);
        System.out.println(list);
    }

    /**
     * 通过document对象查询，注意了，在mongo版本2.x之前用的是DBObject和BasicDBObject对象
     *      2.x版本之后就舍弃了DBObject对象了，全部改用了Document对象
     */
    @Test
    public void getInfo2(){
        Document document = new Document();
//        document.append("name","ss");
        document.append("age",2);
        Query query = new BasicQuery(document);
        Person p = mongoTemplate.findOne(query,Person.class);
        System.out.println(p);
    }

    /**
     * 设置过滤条件查询
     */
    @Test
    public void getInfo3(){
        Document document = new Document();
        document.append("age",new Document("$gte",15).append("$lt",20));  //通过嵌套document的方式完成过滤查询，查询年龄在15（包含）到20之间的所有
        Query query = new BasicQuery(document);
        List<Person> p = mongoTemplate.find(query,Person.class);
        System.out.println(p);
    }

}
