package com.lhw.import_annotation.annotation;

import com.lhw.import_annotation.model.TestB;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：linhw
 * @date ：22.5.5 13:44
 * @description：自定义自动配置注解
 *
 *      直接引入需要的对应的Bean对象的class
 *
 * @modified By：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TestB.class)
public @interface MyEnableAutoConfigByBean {
}
