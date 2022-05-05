package com.lhw.import_annotation.annotation;

import com.lhw.import_annotation.register.MyImportBeanDefinitionRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：linhw
 * @date ：22.5.5 13:54
 * @description：自定义自动配置注解
 *
 *      通过
 *
 * @modified By：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportBeanDefinitionRegister.class)
public @interface MyEnableAutoConfigByDefinitionRegister {
}
