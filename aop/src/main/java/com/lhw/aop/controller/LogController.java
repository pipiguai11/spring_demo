package com.lhw.aop.controller;

import com.lhw.aop.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：linhw
 * @date ：21.12.21 15:07
 * @description：日志测试
 * @modified By：
 */
@RestController
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/test/log/aspect")
    public String test(){
        return logService.testReturn();
    }

    @GetMapping("test/log/aspect2")
    public String test2(){
        return logService.testException();
    }

    @GetMapping("test/log/aspect3")
    public String test3(){
        return logService.testParameters("lhw",18);
    }

}
