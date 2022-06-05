package com.lhw.container.annotations;


import java.lang.annotation.*;

/**
 * @author ：linhw
 * @date ：22.6.5 23:17
 * @description：我的自定义依赖注入注解
 * @modified By：
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyInjectUser {
}
