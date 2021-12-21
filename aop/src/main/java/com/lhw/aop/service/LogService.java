package com.lhw.aop.service;

import com.lhw.aop.annotation.PrintLog;
import com.lhw.aop.dao.LogRepository;
import com.lhw.aop.module.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：linhw
 * @date ：21.12.21 11:32
 * @description：日志服务
 * @modified By：
 */
@Service
public class LogService {

    @Autowired
    LogRepository logRepository;

    public void save(Log log){
        logRepository.save(log);
    }

    @PrintLog(description = "测试成功返回值")
    public String testReturn(){
        return "success";
    }

    @PrintLog(description = "测试方法抛出异常")
    public String testException(){
        int temp = 1/0;
        return "success";
    }

    @PrintLog(description = "测试多参数且成功返回值")
    public String testParameters(String name, int age){
        return name + "_" + age;
    }

}
