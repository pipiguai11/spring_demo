package com.lhw.value.controller;

import com.lhw.value.util.ApplicationUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：linhw
 * @date ：21.12.23 10:56
 * @description：
 * @modified By：
 */
@RestController
public class TestController {

    @GetMapping("/test/get/{beanName}")
    public String getBeanByName(@PathVariable String beanName){
        return ApplicationUtil.getBean(beanName).toString();
    }

}
