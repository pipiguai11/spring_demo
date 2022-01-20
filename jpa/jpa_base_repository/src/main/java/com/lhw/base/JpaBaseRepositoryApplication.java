package com.lhw.base;

import com.lhw.base.repo.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class JpaBaseRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaBaseRepositoryApplication.class, args);
    }

}
