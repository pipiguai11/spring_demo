package com.lhw.base;

import com.lhw.base.module.UserTest;
import com.lhw.base.repo.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.UUID;

@SpringBootTest
class JpaBaseRepositoryApplicationTests {

    @Autowired
    TestRepository repo;

    @Test
    void contextLoads() {
//        save();
        System.out.println(repo.findOne("f5129c15-0f9c-4eab-9cee-831c555c5ddc"));
        System.out.println();
        System.out.println(repo.findOne("3b52d2b3-0161-40dd-8756-a3413a3b4f92"));
    }

    private void save(){
        Random random = new Random();
        for (int i = 0 ; i < 100 ; i ++){
            int temp = random.nextInt(200);
            repo.save(UserTest
                    .builder()
                    .userName("lhw" + temp)
                    .code(UUID.randomUUID().toString())
                    .password("123")
                    .addr("gz")
                    .age(temp)
                    .build());
        }
    }

}
