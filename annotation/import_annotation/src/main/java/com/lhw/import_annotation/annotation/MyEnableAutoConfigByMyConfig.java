package com.lhw.import_annotation.annotation;

import com.lhw.import_annotation.config.MyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：linhw
 * @date ：22.5.5 13:50
 * @description：自定义自动配置注解
 *
 *      通过引入自定义的配置类，可以同时注入多个Bean
 *
 * @modified By：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyConfig.class)
public @interface MyEnableAutoConfigByMyConfig {
}
