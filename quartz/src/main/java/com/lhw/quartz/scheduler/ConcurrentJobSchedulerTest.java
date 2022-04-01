package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.annotation.DisallowConcurrentJob;
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
        JobDetail jobDetail = JobBuilder.newJob(DisallowConcurrentJob.class).withIdentity("concurrentJob","myGroup").build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();

        //再新建一个任务，和一个触发器，使用的同一个Job实例，测试并行
        JobDetail newJobDetail = JobBuilder.newJob(DisallowConcurrentJob.class).withIdentity("concurrentJob2","myGroup").build();
        Trigger newtTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();

        //设置调度器调度任务，并启动调度器
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.scheduleJob(newJobDetail,newtTrigger);
        scheduler.start();

    }

}
