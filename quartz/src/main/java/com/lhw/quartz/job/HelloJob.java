package com.lhw.quartz.job;

import com.lhw.quartz.model.Task;
import org.quartz.*;

import java.text.SimpleDateFormat;

/**
 * @author ：linhw
 * @date ：22.3.31 09:57
 * @description：测试的job
 * @modified By：
 */
public class HelloJob implements Job {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务被调度了--------------------");

        //获取任务信息
        final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String taskId = (String) jobDataMap.get("id");
        Task task = (Task) jobDataMap.get(taskId);

        //获取触发器信息
        final Trigger trigger = context.getTrigger();
        final JobDataMap triggerJobDataMap = trigger.getJobDataMap();
        String cron = (String) triggerJobDataMap.get("cron");

        System.out.println("任务详情：");
        StringBuilder sb = new StringBuilder();
        sb.append("|--- id :" + task.getId() + "\n")
                .append("|--- taskName : " + task.getTaskName() + "\n")
                .append("|--- startTime : " + sdf.format(task.getStartTime()) + "\n")
                .append("|--- endTime" + sdf.format(task.getEndTime()) + "\n")
                .append("|--- status : " + task.getStatus() + "\n")
                .append("|--- taskType : " + task.getTaskType());
        System.out.println(sb.toString());

        System.out.println("触发器详情：");
        StringBuilder triSb = new StringBuilder();
        triSb.append("|--- cron : " + cron);
        System.out.println(triSb.toString());

        System.out.println("-------------------------------分割线-------------------------------------------");
    }
}
