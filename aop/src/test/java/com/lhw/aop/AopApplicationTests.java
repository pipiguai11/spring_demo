package com.lhw.aop;

import com.lhw.aop.dao.LogRepository;
import com.lhw.aop.module.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AopApplicationTests {

    @Autowired
    private LogRepository repository;

    @Test
    void contextLoads() {

        Log log = Log.builder()
                .result("111")
                .logTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .author("lhw")
                .description("222")
                .remark("333")
                .url("request.getRequestURL().toString()")
                .ip("request.getLocalAddr()")
                .exMessage("null == ex ? null : ex.getMessage()")
                .build();
        repository.save(log);

    }

}
