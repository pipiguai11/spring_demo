package com.lhw.jwt_auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：linhw
 * @date ：22.4.18 15:04
 * @description：测试接口
 * @modified By：
 */
@RestController
@RequestMapping("user")
public class TestController {

    @RequestMapping(name = "/query", method = RequestMethod.GET)
    public String query(){
        return "success";
    }

//    @RequestMapping(name = "/find", method = RequestMethod.GET)
//    public String find(){
//        return "success";
//    }

}
