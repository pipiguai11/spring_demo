package com.lhw.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


/**
 * @author ：linhw
 * @date ：22.3.23 10:32
 * @description：存储桶操作
 * @modified By：
 */
@Configuration
public class MongoBucketConfig {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public GridFSBucket gridFSBucket() {
        MongoDatabase database = mongoTemplate.getDb();
        return GridFSBuckets.create(database);
    }

//    @Bean
//    public GridFSBucket getGridFSBucket(MongoClient client){
//        SimpleMongoClientDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(client,"lhw_mongo");
//        MongoDatabase database = mongoDbFactory.getMongoDatabase();
//        GridFSBucket bucket = GridFSBuckets.create(database);
//        return bucket;
//    }

}
