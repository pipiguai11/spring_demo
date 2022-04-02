package com.lhw.quartz.trigger;

import org.quartz.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：linhw
 * @date ：22.4.1 15:39
 * @description：触发器处理器
 *
 *      不论是SimpleTrigger还是CronTrigger都是通过TriggerBuilder构建的，只是内部用的调度器不一样
 *      SimpleTrigger用的SimpleScheduleBuilder
 *      CronTrigger用的CronScheduleBuilder
 *
 * @modified By：
 */
public class TriggerHandler {

    private static final String TRIGGER_NAME = "trigger";
    private static final String GROUP_NAME = "group";

    private static Map<String, Long> simpleTriggerInstructionsMap = new ConcurrentHashMap<>(16);

    //默认重复周期5秒
    private static int DEFAULT_SECOND = 5;

    private static String DEFAULT_CRON = "0/10 * * * * ?";

    /**
     * 构建简单触发器（即时启动）  SimpleTrigger
     *      在创建完成后马上启动
     *
     *
     *
     * @return
     */
    public static Trigger createSimpleTrigger(TriggerInstructionsEnum... instructionsEnums) {
        return createSimpleTrigger(TRIGGER_NAME, GROUP_NAME, null, null, instructionsEnums);
    }

    public static Trigger createSimpleTrigger(String triggerName, TriggerInstructionsEnum... instructionsEnums) {
        return createSimpleTrigger(triggerName, GROUP_NAME, null, null ,instructionsEnums);
    }

    /**
     * 创建定时任务
     *
     *      指定运行时间，包括启动时间以及结束时间
     *
     * @param startTime
     * @param endTime
     * @param instructionsEnums
     * @return
     */
    public static Trigger createSimpleTrigger(Date startTime, Date endTime, TriggerInstructionsEnum... instructionsEnums) {
        return createSimpleTrigger(TRIGGER_NAME, GROUP_NAME, startTime, endTime, instructionsEnums);
    }

    public static Trigger createSimpleTrigger(String triggerName, String group, TriggerInstructionsEnum... instructionsEnums) {
        return createSimpleTrigger(triggerName, group, null, null, instructionsEnums);
    }

    public static Trigger createSimpleTrigger(String triggerName, String group, Date startTime, Date endTime, TriggerInstructionsEnum... instructionsEnums) {
        if (Objects.nonNull(startTime) && Objects.nonNull(endTime) && startTime.after(endTime)){
            throw new IllegalArgumentException("endTime is not allow before startTime");
        }

        String triggerKey = group + "_" + triggerName;
        buildTriggerInstruction(triggerKey, instructionsEnums);
        ScheduleBuilder scheduleBuilder = getSchedulerBuilder(triggerKey);

        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger();
        if (startTime != null) {
            triggerBuilder.startAt(startTime);
        }
        if (endTime != null) {
            triggerBuilder.endAt(endTime);
        }

        //withIntervalInSeconds指定以秒为单位重复执行，repeatForever指定无限重复
//        return TriggerBuilder.newTrigger()
//                .withIdentity(triggerName, group)
//                .withSchedule(scheduleBuilder).build();
        return triggerBuilder.withIdentity(triggerName, group)
                .withSchedule(scheduleBuilder).build();
    }

    /**
     * 创建定时触发器
     * @return
     */
    public static Trigger createCronTrigger(){
        return createCronTrigger(TRIGGER_NAME,GROUP_NAME);
    }

    public static Trigger createCronTrigger(String triggerName){
        return createCronTrigger(triggerName, GROUP_NAME);
    }

    public static Trigger createCronTrigger(String triggerName, String groupName) {
        //定时调度，每10秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(DEFAULT_CRON)
                .withMisfireHandlingInstructionDoNothing();
        //构建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName,groupName)
                .withSchedule(cronScheduleBuilder).build();
        return trigger;
    }

    /**
     * 往触发器中添加信息
     * @param trigger
     * @param key
     * @param value
     */
    public static void addMapToJobDataMap(Trigger trigger, String key, Object value){
        trigger.getJobDataMap().putIfAbsent(key, value);
    }

    public static void addAllMapToJobDataMap(Trigger trigger, Map<String,Object> map){
        trigger.getJobDataMap().putAll(map);
    }

    /**
     * 根据指令列表构建个性化设置（使用位运算进行存储）
     * @param key
     * @param instructionsEnums
     */
    private static void buildTriggerInstruction(String key, TriggerInstructionsEnum... instructionsEnums){
        long instruction = 0;
        for (TriggerInstructionsEnum instructionsEnum : instructionsEnums) {
            //先按照枚举的ordinal值按位移动
            long temp = 1 << instructionsEnum.ordinal();
            //然后进行或运算，得到一个新的数值
            instruction = temp | instruction;
        }
        //将值放入map中
        simpleTriggerInstructionsMap.putIfAbsent(key, instruction);
    }

    /**
     * 根据触发器策略构建调度器Builder
     *
     *      通过反射机制以及位运算构建
     *
     * @param key
     */
    private static ScheduleBuilder getSchedulerBuilder(String key){
        Long instruction = simpleTriggerInstructionsMap.get(key);
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();

        if (instruction == 0){
            //默认使用以下策略
            simpleScheduleBuilder.withIntervalInSeconds(DEFAULT_SECOND).repeatForever();
            return simpleScheduleBuilder;
        }

        Class<?> simpleScheduleBuilderClazz = simpleScheduleBuilder.getClass();
        try {
            for (int i = 0; i < TriggerInstructionsEnum.getLength() ; ++i){
                //如果当前二进制数最后一位为0，直接进入下一次循环
                if ((instruction & 1) == 0){
                    instruction = instruction >> 1;
                    continue;
                }

                Number arg = null;
                Method method = null;
                if (TriggerInstructionsEnum.INTERVAL_IN_MILLISECONDS.ordinal() == i){
                    arg = DEFAULT_SECOND * 1000;
                    method = simpleScheduleBuilderClazz
                            .getDeclaredMethod(TriggerInstructionsEnum.getInstruction(i).getInstruction(), long.class);
                    method.invoke(simpleScheduleBuilder,(long)arg);
                }else if (TriggerInstructionsEnum.INTERVAL_IN_HOURS.ordinal() == i ||
                        TriggerInstructionsEnum.INTERVAL_IN_MINUTES.ordinal() == i ||
                        TriggerInstructionsEnum.INTERVAL_IN_SECONDS.ordinal() == i) {
                    arg = DEFAULT_SECOND;
                    method = simpleScheduleBuilderClazz
                            .getDeclaredMethod(TriggerInstructionsEnum.getInstruction(i).getInstruction(), int.class);
                    method.invoke(simpleScheduleBuilder, (int)arg);
                }else {
                    method = simpleScheduleBuilderClazz
                            .getDeclaredMethod(TriggerInstructionsEnum.getInstruction(i).getInstruction());
                    method.invoke(simpleScheduleBuilder);
                }

                //无符号右移一位
                instruction = instruction >> 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return simpleScheduleBuilder;
    }

}
