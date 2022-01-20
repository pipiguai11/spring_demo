package com.lhw.oracle;

import com.lhw.oracle.module.UserTest;
import com.lhw.oracle.service.UserTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DatasourceOracleApplicationTests {

    @Autowired
    UserTestService service;

    @Test
    void contextLoads() {

        service.saveUser(UserTest.builder().code(UUID.randomUUID().toString()).age(18).password("123").addr("gz").userName("lhw").build());

    }

}
