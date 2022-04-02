package com.lhw.quartz.trigger;

import org.quartz.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：linhw
 * @date ：22.4.1 15:39
 * @description：触发器处理器
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
     * 构建简单触发器
     * @return
     */
    public static Trigger createSimpleTrigger(InstructionsEnum... instructionsEnums){
        return createSimpleTrigger(TRIGGER_NAME, GROUP_NAME, instructionsEnums);
    }

    public static Trigger createSimpleTrigger(String triggerName, InstructionsEnum... instructionsEnums){
        return createSimpleTrigger(triggerName, GROUP_NAME);
    }

    public static Trigger createSimpleTrigger(String triggerName, String group, InstructionsEnum... instructionsEnums){

        String triggerKey = group + "_" + triggerName;
        buildTriggerInstruction(triggerKey, instructionsEnums);
        ScheduleBuilder scheduleBuilder = getSchedulerBuilder(triggerKey);

        //withIntervalInSeconds指定以秒为单位重复执行，repeatForever指定无限重复
        return TriggerBuilder.newTrigger()
                .withIdentity(triggerName, group)
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
    private static void buildTriggerInstruction(String key, InstructionsEnum... instructionsEnums){
        long instruction = 0;
        for (InstructionsEnum instructionsEnum : instructionsEnums) {
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
            simpleScheduleBuilder.withIntervalInSeconds(DEFAULT_SECOND).repeatForever();
            return simpleScheduleBuilder;
        }

        Class<?> simpleScheduleBuilderClazz = simpleScheduleBuilder.getClass();
        try {
            for (int i = 0 ; i < InstructionsEnum.getLength() ; ++i){
                //如果当前二进制数最后一位为0，直接进入下一次循环
                if ((instruction & 1) == 0){
                    instruction = instruction >> 1;
                    continue;
                }

                Number arg = null;
                Method method = null;
                if (InstructionsEnum.INTERVAL_IN_MILLISECONDS.ordinal() == i){
                    arg = DEFAULT_SECOND * 1000;
                    method = simpleScheduleBuilderClazz
                            .getDeclaredMethod(InstructionsEnum.getInstruction(i).getInstruction(), long.class);
                    method.invoke(simpleScheduleBuilder,(long)arg);
                }else if (InstructionsEnum.INTERVAL_IN_HOURS.ordinal()== i ||
                        InstructionsEnum.INTERVAL_IN_MINUTES.ordinal() == i ||
                        InstructionsEnum.INTERVAL_IN_SECONDS.ordinal() == i) {
                    arg = DEFAULT_SECOND;
                    method = simpleScheduleBuilderClazz
                            .getDeclaredMethod(InstructionsEnum.getInstruction(i).getInstruction(), int.class);
                    method.invoke(simpleScheduleBuilder, (int)arg);
                }else {
                    method = simpleScheduleBuilderClazz
                            .getDeclaredMethod(InstructionsEnum.getInstruction(i).getInstruction());
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
