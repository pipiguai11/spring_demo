package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.HelloJob2;
import com.lhw.quartz.job_factory.MyJobFactory;
import com.lhw.quartz.jobdetail.JobDetailHandler;
import com.lhw.quartz.trigger.TriggerHandler;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author ：linhw
 * @date ：22.4.1 11:31
 * @description：使用自定义Job工厂的调度器实现测试
 * @modified By：
 */
public class UseMyJobFactorySchedulerTest {

    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().putIfAbsent("skey","myScheduler");
        //设置自定义的Job工厂，用于Job的初始化，也就是JobDetail被调度时生成Job实例的流程
        scheduler.setJobFactory(new MyJobFactory());

        //新建一个任务，和一个触发器
        JobDetail newJobDetail = JobDetailHandler.createJobDetail(HelloJob2.class, "myJob2","myGroup");
        //这里就需要注意了，因为我上面用的是自定义的Job初始化工厂，在初始化的时候没有调用setting方法进行属性绑定，因此这个Job无法直接通过属性去获取该值，只能是通过JobDataMap对象去获取
        newJobDetail.getJobDataMap().putIfAbsent("name","lhw");
        Trigger newtTrigger = TriggerHandler.createSimpleTrigger();

        //设置调度器调度任务，并启动调度器
        scheduler.scheduleJob(newJobDetail,newtTrigger);
        scheduler.start();

    }

}
