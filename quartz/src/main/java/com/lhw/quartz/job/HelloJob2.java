package com.lhw.quartz.job;

import com.lhw.quartz.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        System.out.println("time : " + DateUtil.formatToString(new Date()));
    }

    public void setName(String name){
        this.name = name;
    }

}
