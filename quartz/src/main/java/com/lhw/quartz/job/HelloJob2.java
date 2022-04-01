package com.lhw.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ：linhw
 * @date ：22.3.31 13:19
 * @description：
 * @modified By：
 */
public class HelloJob2 implements Job {

    private String name ;

    //注意了，这个Job类必须提供无参构造函数，因为在JobFactory初始化的时候是调用的newInstance方法
//    public HelloJob2(String name){
//        this.name = name;
//    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("HelloJob2任务被调度了。。。。。。。。。。。。。。。。");
        System.out.println("hello world");
        System.out.println("name = " + name);
    }

    public void setName(String name){
        this.name = name;
    }

}
