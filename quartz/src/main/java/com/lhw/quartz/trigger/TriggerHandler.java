package com.lhw.quartz.trigger;

import org.quartz.*;

import java.util.Map;

/**
 * @author ：linhw
 * @date ：22.4.1 15:39
 * @description：触发器处理器
 * @modified By：
 */
public class TriggerHandler {

    private static final String TRIGGER_NAME = "trigger";
    private static final String GROUP_NAME = "group";

    public static Trigger createSimpleTrigger(){
        return createSimpleTrigger(TRIGGER_NAME, GROUP_NAME);
    }

    public static Trigger createSimpleTrigger(String triggerName){
        return createSimpleTrigger(triggerName, GROUP_NAME);
    }

    public static Trigger createSimpleTrigger(String triggerName, String group){
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, group)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();
        return trigger;
    }

    public static Trigger createCronTrigger(){
        return createCronTrigger(TRIGGER_NAME,GROUP_NAME);
    }

    public static Trigger createCronTrigger(String triggerName){
        return createCronTrigger(triggerName, GROUP_NAME);
    }

    public static Trigger createCronTrigger(String triggerName, String groupName) {
        //定时调度，每5秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                .withMisfireHandlingInstructionDoNothing();
        //构建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName,groupName)
                .withSchedule(cronScheduleBuilder).build();
        return trigger;
    }

    public static void addMapToJobDataMap(Trigger trigger, String key, Object value){
        trigger.getJobDataMap().putIfAbsent(key, value);
    }

    public static void addAllMapToJobDataMap(Trigger trigger, Map<String,Object> map){
        trigger.getJobDataMap().putAll(map);
    }

}
