package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.HelloJob;
import com.lhw.quartz.job.HelloJob2;
import com.lhw.quartz.jobdetail.JobDetailHandler;
import com.lhw.quartz.model.Task;
import com.lhw.quartz.trigger.TriggerHandler;
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

        Task task = initTask();
        //构建任务，指定任务对象（HelloJob），调度器会去调度它的execute方法
        JobDetail jobDetail = JobDetailHandler.createJobDetail(HelloJob.class);
        JobDetailHandler.addMapToJobDataMap(jobDetail, "id", task.getId());
        JobDetailHandler.addMapToJobDataMap(jobDetail, task.getId(), task);
        //构建触发器
        Trigger trigger = TriggerHandler.createCronTrigger();
        TriggerHandler.addMapToJobDataMap(trigger,"cron","0/5 * * * * ? ");

        //新建一个任务，和一个触发器
        JobDetail newJobDetail = JobDetailHandler.createJobDetail(HelloJob2.class, "myJob2", "myGroup");
        //这里设置的key-value是在初始化的时候被setting到Job（HelloJob2）对象中的，可以不用通过JobDataMap去获取，当然要用它获取也是可以的
        JobDetailHandler.addMapToJobDataMap(newJobDetail, "name", "lhw");
        Trigger newtTrigger = TriggerHandler.createSimpleTrigger("trigger1", "group1");

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
