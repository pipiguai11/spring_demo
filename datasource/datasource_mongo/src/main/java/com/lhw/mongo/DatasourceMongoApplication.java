package com.lhw.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DatasourceMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourceMongoApplication.class, args);
    }

}
