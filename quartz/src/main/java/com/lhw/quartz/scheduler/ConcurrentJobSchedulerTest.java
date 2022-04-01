package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.annotation.DisallowConcurrentJob;
import com.lhw.quartz.jobdetail.JobDetailHandler;
import com.lhw.quartz.trigger.TriggerHandler;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author ：linhw
 * @date ：22.4.1 14:25
 * @description：并行Job调度器测试
 * @modified By：
 */
public class ConcurrentJobSchedulerTest {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().putIfAbsent("skey","myScheduler");

        //新建一个任务，和一个触发器
        JobDetail jobDetail = JobDetailHandler.createJobDetail(DisallowConcurrentJob.class, "concurrentJob","myGroup");
        Trigger trigger = TriggerHandler.createSimpleTrigger();

        //再新建一个任务，和一个触发器，使用的同一个Job实例，测试并行
        JobDetail newJobDetail = JobDetailHandler.createJobDetail(DisallowConcurrentJob.class, "concurrentJob2","myGroup");
        Trigger newtTrigger = TriggerHandler.createSimpleTrigger("trigger2");

        //设置调度器调度任务，并启动调度器
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.scheduleJob(newJobDetail,newtTrigger);
        scheduler.start();

    }

}
