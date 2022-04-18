package com.lhw.jwt_auth;

import com.lhw.jwt_auth.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class JwtAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthApplication.class, args);
    }

}
