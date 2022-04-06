package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.HelloJob2;
import com.lhw.quartz.jobdetail.JobDetailHandler;
import com.lhw.quartz.listener.job.MyJobListener;
import com.lhw.quartz.listener.trigger.MyTriggerListener;
import com.lhw.quartz.trigger.TriggerHandler;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * @author ：linhw
 * @date ：22.4.6 10:29
 * @description：注册Job监听器和Trigger监听器测试
 * @modified By：
 */
public class ListenerSchedulerTest {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = initScheduler();
//        jobListener(scheduler);
        triggerListener(scheduler);
        scheduler.start();
    }

    private static void jobListener(Scheduler scheduler) throws SchedulerException {
        //订阅group组的所有job
        scheduler.getListenerManager().addJobListener(new MyJobListener(), GroupMatcher.groupEquals("group"));
    }

    private static void triggerListener(Scheduler scheduler) throws SchedulerException {
        //订阅group组的所有trigger
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(), GroupMatcher.groupEquals("group"));
    }

    private static Scheduler initScheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().putIfAbsent("skey","listenerScheduler");

        JobDetail jobDetail = JobDetailHandler.createJobDetail(HelloJob2.class);
        JobDetailHandler.addMapToJobDataMap(jobDetail,"name","lhw");

        Trigger trigger = TriggerHandler.createSimpleTrigger();

        scheduler.scheduleJob(jobDetail,trigger);
        return scheduler;
    }

}
