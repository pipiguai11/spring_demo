package com.lhw.actuator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：linhw
 * @date ：22.4.24 13:50
 * @description：将bean注入到ioc容器中，为了给actuator监听到
 *
 *      正常来说在http://localhost:8081/actuator/z/beans页面下是可以看到这个Bean的信息的。
 *
 * @modified By：
 */
@Configuration
public class TestRegisterBean {

    @Bean
    public TestBean testBean() {
        return new TestBean();
    }

}

class TestBean {
    private String name;
}
