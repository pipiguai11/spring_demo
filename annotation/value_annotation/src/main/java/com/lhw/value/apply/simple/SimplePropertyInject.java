package com.lhw.value.apply.simple;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：linhw
 * @date ：21.12.23 10:35
 * @description：简单属性注入
 * @modified By：
 */
@Configuration
@Data
public class SimplePropertyInject {

//-----------------------------------属性注入 start-----------------------------------------------
    //读取配置文件中的配置
//    @Value("${lhw.simple.userName}")
    private String userName;

    //读取配置文件中的配置，同时设置默认值，如果配置文件中找不到的话，则默认设置成defaultValue
//    @Value("${lhw.default.value:defaultValue}")
    private String defaultValue;
//-----------------------------------属性注入 end-----------------------------------------------


//-----------------------------------setter方法注入 start-----------------------------------------------
    //读取配置文件中的配置
//    @Value("${lhw.simple.userName}")
    public void setUserName(String userName){
        this.userName = userName;
    }

    //读取配置文件中的配置，同时设置默认值，如果配置文件中找不到的话，则默认设置成defaultValue
//    @Value("${lhw.default.value:defaultValue}")
    public void setDefaultValue(String defaultValue){
        this.defaultValue = defaultValue;
    }
//-----------------------------------setter方法注入 end-----------------------------------------------


//-----------------------------------构造函数注入 start-----------------------------------------------
    public SimplePropertyInject(@Value("${lhw.simple.userName}")String userName,
                                @Value("${lhw.default.value:defaultValue}")String defaultValue){
        this.userName = userName;
        this.defaultValue = defaultValue;
    }
//-----------------------------------构造函数注入 end-----------------------------------------------




    @Override
    public String toString(){
        return "userName = " + userName + " , defaultValue = " + defaultValue ;
    }

}
