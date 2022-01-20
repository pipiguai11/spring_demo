package com.lhw.filterbase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：linhw
 * @date ：22.1.20 15:09
 * @description：
 * @modified By：
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test/filter", method = RequestMethod.GET)
    public String filter(){
        return "success";
    }

}
