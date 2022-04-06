package com.lhw.quartz.listener.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * @author ：linhw
 * @date ：22.4.2 17:25
 * @description：我的自定义Job监听器
 * @modified By：
 */
public class MyJobListener extends JobListenerSupport {

    /**
     * listener在运行时会向调度程序注册，并且必须给出一个名称
     * 所以这个getName是必须实现的。
     * @return
     */
    @Override
    public String getName() {
        return "myJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("即将执行任务【" + context.getJobDetail().getKey() + "】,当JobDetail即将执行时由Scheduler调用（已发生关联的Trigger ） " +
                "如果 Job 的执行被TriggerListener否决，则不会调用此方法");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("【" + context.getJobDetail().getKey() + " 】当JobDetail即将执行（已发生关联的Trigger ）时由Scheduler调用，但TriggerListener否决了它的执行");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("【" + context.getJobDetail().getKey() + "】执行完毕，在JobDetail执行后由Scheduler调用，并为关联的Trigger的triggered(xx)方法已被调用");
    }

}
