package com.lhw.import_annotation.config;

import com.lhw.import_annotation.model.TestA;
import com.lhw.import_annotation.model.TestB;
import org.springframework.context.annotation.Bean;

/**
 * @author ：linhw
 * @date ：22.5.5 13:48
 * @description：配置类
 *
 *      这里通过@Bean注解注入Bean对象
 *
 * @modified By：
 */
public class MyConfig {

    @Bean
    public TestA testA() {
        return new TestA();
    }

    @Bean
    public TestB testB() {
        return new TestB();
    }

}
