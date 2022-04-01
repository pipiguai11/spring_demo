package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.HelloJob;
import com.lhw.quartz.job.HelloJob2;
import com.lhw.quartz.model.Task;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author ：linhw
 * @date ：22.3.31 10:34
 * @description：调度器工厂测试
 * @modified By：
 */
public class SchedulerFactoryTest {

    public static void main(String[] args) throws SchedulerException {
        //获取调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().putIfAbsent("skey","myScheduler");

        //定时调度，每5秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                .withMisfireHandlingInstructionDoNothing();
        //构建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger","group1")
                .usingJobData("triKey1","lalalalal")
                .withSchedule(cronScheduleBuilder).build();
        trigger.getJobDataMap().putIfAbsent("cron","0/5 * * * * ? ");

        Task task = initTask();
        //构建任务，指定任务对象（HelloJob），调度器会去调度它的execute方法
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .usingJobData("id",task.getId())
                .withIdentity("myJob","myGroup")
                .build();
        jobDetail.getJobDataMap().putIfAbsent(task.getId(), task);

        //新建一个任务，和一个触发器
        JobDetail newJobDetail = JobBuilder.newJob(HelloJob2.class).withIdentity("myJob2","myGroup").build();
        newJobDetail.getJobDataMap().putIfAbsent("name","lhw");
        Trigger newtTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tv1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();

        //设置调度器调度任务，并启动调度器
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.scheduleJob(newJobDetail,newtTrigger);
        scheduler.start();
    }

    private static Task initTask(){
        //结束时间为3小时之后
        Calendar now = Calendar.getInstance();
        Date startDate = new Date();
        now.setTime(startDate);
        now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) + 3);

        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setTaskName("测试任务");
        task.setStartTime(startDate);
        task.setEndTime(now.getTime());
        task.setStatus(2);
        task.setTaskType(2);
        return task;
    }

}
