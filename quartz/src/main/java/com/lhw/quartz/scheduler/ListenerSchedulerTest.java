package com.lhw.quartz.scheduler;

import com.lhw.quartz.job.HelloJob2;
import com.lhw.quartz.jobdetail.JobDetailHandler;
import com.lhw.quartz.listener.job.MyJobListener;
import com.lhw.quartz.trigger.TriggerHandler;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @author ：linhw
 * @date ：22.4.6 10:29
 * @description：注册Job监听器和Trigger监听器测试
 * @modified By：
 */
public class ListenerSchedulerTest {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().putIfAbsent("skey","listenerScheduler");

        JobDetail jobDetail = JobDetailHandler.createJobDetail(HelloJob2.class);
        JobDetailHandler.addMapToJobDataMap(jobDetail,"name","lhw");

        Trigger trigger = TriggerHandler.createSimpleTrigger();

        scheduler.scheduleJob(jobDetail,trigger);
        //订阅group组的所有job
        scheduler.getListenerManager().addJobListener(new MyJobListener(), GroupMatcher.groupEquals("group"));
        scheduler.start();

    }

}
