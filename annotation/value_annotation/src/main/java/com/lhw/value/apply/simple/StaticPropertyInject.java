package com.lhw.value.apply.simple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：linhw
 * @date ：21.12.23 13:13
 * @description：静态属性注入
 * @modified By：
 */
@Configuration
public class StaticPropertyInject {

//    @Value("${lhw.static.value:defaultValue}")
    private static String value;

    @Value("${lhw.static.value:defaultValue}")
    public void setValue(String value){
        StaticPropertyInject.value = value;
    }

    @Override
    public String toString(){
        return "value = " + value;
    }

}
