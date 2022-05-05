package com.lhw.import_annotation.model;

/**
 * @author ：linhw
 * @date ：22.5.5 13:46
 * @description：测试注入Bean
 * @modified By：
 */
public class TestA {

    private String name;

    private TestB testB;

    public TestA() {
        System.out.println("TestA被初始化了");
    }

}
