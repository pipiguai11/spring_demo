package com.lhw.import_annotation.annotation;

import com.lhw.import_annotation.selector.MyImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：linhw
 * @date ：22.5.5 13:37
 * @description：自定义自动配置注解
 *
 *      通过ImportSelector对象的selectImports方法可以读取配置文件达到动态自动化配置的效果
 *
 * @modified By：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)
public @interface MyEnableAutoConfig {
}
