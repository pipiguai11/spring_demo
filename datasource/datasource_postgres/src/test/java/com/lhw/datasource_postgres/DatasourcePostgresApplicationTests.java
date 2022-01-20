package com.lhw.datasource_postgres;

import com.lhw.datasource_postgres.module.UserTest;
import com.lhw.datasource_postgres.service.UserTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DatasourcePostgresApplicationTests {

    @Autowired
    UserTestService service;

    @Test
    void contextLoads() {

        service.saveUser(UserTest.builder()
                .userName("lhw")
                .password("123")
                .code(UUID.randomUUID().toString())
                .addr("gz")
                .age(18)
                .build());
    }

}
