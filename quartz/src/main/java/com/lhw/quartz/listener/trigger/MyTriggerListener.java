package com.lhw.quartz.listener.trigger;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

/**
 * @author ：linhw
 * @date ：22.4.2 17:33
 * @description：我的自定义触发器监听器
 * @modified By：
 */
public class MyTriggerListener extends TriggerListenerSupport {
    /**
     * 同样的，在listener在运行时会向调度程序注册，并且必须给出一个名称
     * @return
     */
    @Override
    public String getName() {
        return "myTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("【" + trigger.getKey() + "】触发器被调度了，Trigger由Scheduler调用，并且它关联的JobDetail即将被执行. " +
                "它在此接口的vetoJobExecution(..)方法之前调用");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        System.out.println("当Trigger时由Scheduler调用，并且它关联的JobDetail即将被执行。如果实现否决了执行（通过返回true ），则不会调用作业的 execute 方法。" +
                "在此接口的triggerFired(..)方法之后调用它");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("【" + trigger.getKey() + "】触发器调用失败");
    }

    @Override
    public void triggerComplete(
            Trigger trigger,
            JobExecutionContext context,
            Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("【" + trigger.getKey() + "】触发器执行【" + context.getJobDetail().getKey() + "】完毕");
    }

}
