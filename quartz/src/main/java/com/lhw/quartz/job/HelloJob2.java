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
