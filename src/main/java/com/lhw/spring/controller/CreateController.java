package com.lhw.spring.controller;

import com.lhw.spring.service.CreateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：linhw
 * @date ：21.11.19 13:36
 * @description：测试控制层
 * @modified By：
 */
@RestController
public class CreateController {

    @Autowired
    CreateFacade facade;

    /**
     * 简单测试
     * @return
     */
    @RequestMapping(value = "/test1/message", method = RequestMethod.GET)
    public String test1(){
        return "success";
    }

    /**
     * 创建一个新的接口，创建完之后可以直接访问的，路径为/lhw/create，用法就和这里的接口【test1/message】一样
     * @throws Exception
     */
    @RequestMapping(value = "/test2/create", method = RequestMethod.GET)
    public void test2() throws Exception {
        facade.createController();
    }

}
