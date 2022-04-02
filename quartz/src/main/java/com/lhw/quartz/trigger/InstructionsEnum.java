package com.lhw.quartz.trigger;

/**
 * @author ：linhw
 * @date ：22.4.1 15:39
 * @description：触发器触发执行枚举
 * @modified By：
 */

public enum InstructionsEnum {

    REPEAT_FOREVER(0,"重复执行", "repeatForever"),
    INTERVAL_IN_HOURS(1,"间隔n个小时执行一次","withIntervalInHours"),
    INTERVAL_IN_MILLISECONDS(2,"间隔n毫秒执行一次","withIntervalInMilliseconds"),
    INTERVAL_IN_MINUTES(3,"间隔n分钟执行一次","withIntervalInMinutes"),
    INTERVAL_IN_SECONDS(4,"间隔n秒执行一次","withIntervalInSeconds"),

    MISFIRE_HANDLING_INSTRUCTION_FIRE_NOW(5,"指示Scheduler在发生错误触发的情况下， " +
            "SimpleTrigger现在希望由Scheduler触发。" +
            "注意：此指令通常仅用于“一次性”（非重复）触发器。" +
            "如果它用于重复计数 > 0 的触发器，则它等效于指令","withMisfireHandlingInstructionFireNow"),
    MISFIRE_HANDLING_INSTRUCTION_IGNORE_MISFIRES(6,"调度程序将简单地尝试尽快触发它，然后更新触发器，就像它在适当的时间触发一样。" +
            "注意：如果触发器使用此指令，并且它错过了几次预定的触发，" +
            "则可能会发生几次快速触发，因为触发器试图赶上它本来的位置。" +
            "例如，一个每 15 秒触发一次的 SimpleTrigger，" +
            "如果它失败了 5 分钟，一旦有机会触发，它就会触发 20 次。","withMisfireHandlingInstructionIgnoreMisfires"),
    MISFIRE_HANDLING_INSTRUCTION_NEXT_WITH_EXISTING_COUNT(7,"指示Scheduler在发生错误触发的情况下， " +
            "SimpleTrigger希望被重新调度到“现在”之后的下一个调度时间 - 考虑到任何关联的Calendar ，" +
            "并且重复计数保持不变。" +
            "注意/警告：如果触发器的结束时间已经到来，" +
            "此指令可能会导致Trigger直接进入“完成”状态。","withMisfireHandlingInstructionNextWithExistingCount"),
    MISFIRE_HANDLING_INSTRUCTION_NEXT_WITH_REMAINING_COUNT(8,"指示Scheduler在发生错误触发的情况下， " +
            "SimpleTrigger希望被重新调度到“现在”之后的下一个调度时间" +
            "注意/警告：如果错过了所有触发时间，" +
            "此指令可能会导致Trigger直接进入“完成”状态","withMisfireHandlingInstructionNextWithRemainingCount"),
    MISFIRE_HANDLING_INSTRUCTION_NOW_WITH_EXISTING_COUNT(9,"指示Scheduler在发生错误触发的情况下， " +
            "SimpleTrigger希望被重新调度到“现在”（即使关联的Calendar不包括“现在”），" +
            "重复计数保持不变。但是，这确实遵守Trigger结束时间，因此如果“现在”在结束时间之后， Trigger将不会再次触发 " +
            "注意：使用此指令会导致触发器“忘记”它最初设置的开始时间和重复计数","withMisfireHandlingInstructionNowWithExistingCount"),
    MISFIRE_HANDLING_INSTRUCTION_NOW_WITH_REMAINING_COUNT(10,"指示Scheduler在发生错误触发的情况下， " +
            "SimpleTrigger希望被重新调度到“现在”" +
            "如果“现在”在结束时间之后， Trigger将不会再次触发。" +
            "注意：使用该指令会导致触发器“忘记”它最初设置的开始时间和重复计数。" +
            "   相反，触发器上的重复计数将更改为剩余的重复计数," +
            "注意：如果错过了所有重复触发时间，" +
            "   此指令可能会导致Trigger在触发“现在”后进入“完成”状态","withMisfireHandlingInstructionNowWithRemainingCount");



    private int _id;
    private String desc;
    private String instruction;

    InstructionsEnum(int id, String desc, String instruction) {
        this._id = id;
        this.desc = desc;
        this.instruction = instruction;
    }

    public static InstructionsEnum getInstruction(int id) {
        for (InstructionsEnum value : InstructionsEnum.values()) {
            if (value.getId() == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("Instruction id is no exist");
    }

    public static int getLength(){
        return values().length;
    }

    public String getInstruction(){
        return instruction;
    }

    public int getId(){
        return _id;
    }

}
