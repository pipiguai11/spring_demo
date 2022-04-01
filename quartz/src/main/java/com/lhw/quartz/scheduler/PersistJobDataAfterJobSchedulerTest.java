package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.annotation.PersistJobDataAfterJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author ：linhw
 * @date ：22.4.1 14:35
 * @description：动态更新JobDataMap值的调度器测试
 * @modified By：
 */
public class PersistJobDataAfterJobSchedulerTest {

    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().putIfAbsent("skey","myScheduler");

        //新建一个任务，和一个触发器
        JobDetail jobDetail = JobBuilder.newJob(PersistJobDataAfterJob.class).withIdentity("concurrentJob","myGroup").build();
        jobDetail.getJobDataMap().putIfAbsent(PersistJobDataAfterJob.TEST_NUM,0);
        jobDetail.getJobDataMap().putIfAbsent(PersistJobDataAfterJob.TEST_NAME,"lhw");

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();

        //设置调度器调度任务，并启动调度器
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }

}
