package com.lhw.quartz.listener.scheduler;

import org.quartz.*;
import org.quartz.listeners.SchedulerListenerSupport;

/**
 * @author ：linhw
 * @date ：22.4.6 11:01
 * @description：我的自定义调度器监听器
 * @modified By：
 */
public class MySchedulerListener extends SchedulerListenerSupport {
    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("调度job，调度的触发器为：【" + trigger.getKey() + "】");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("job未调度");
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("【" + trigger.getKey() + "】触发器结束");
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {

    }


    @Override
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("新增Job，名字为【" + jobDetail.getKey() + "】");
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        System.out.println("删除Job，名字为【" + jobKey + "】");
    }

    @Override
    public void schedulerStarted() {
        System.out.println("调度器已启动");
    }

    @Override
    public void schedulerStarting() {
        System.out.println("调度器正在启动");
    }

}
