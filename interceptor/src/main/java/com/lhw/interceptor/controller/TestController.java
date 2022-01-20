package com.lhw.interceptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：linhw
 * @date ：22.1.20 15:22
 * @description：
 * @modified By：
 */
@RestController
public class TestController {

    @RequestMapping(value = "/get" , method = RequestMethod.GET)
    public String test(){
        return "success";
    }

    @RequestMapping(value = "/get2" , method = RequestMethod.GET)
    public String test2(){
        return "success";
    }

}
