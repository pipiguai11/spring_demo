package com.lhw.aop.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface PrintLog {

    /**
     * 日志描述
     * @return
     */
    String description() default "";

}
