package com.lhw.cors.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：linhw
 * @date ：22.4.6 14:26
 * @description：测试
 * @modified By：
 */
@RestController
@RequestMapping("/cors")
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String testCors(){
        return "success";
    }

}
