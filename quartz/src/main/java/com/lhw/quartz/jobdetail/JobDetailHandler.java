package com.lhw.quartz.jobdetail;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

import java.util.Map;

/**
 * @author ：linhw
 * @date ：22.4.1 15:39
 * @description：JobDetail处理器
 * @modified By：
 */
public class JobDetailHandler {

    private static final String JOB_NAME = "my_job";
    private static final String JOB_GROUP = "group";

    public static JobDetail createJobDetail(Class<? extends Job> clazz){
        return createJobDetail(clazz, JOB_NAME, JOB_GROUP);
    }

    public static JobDetail createJobDetail(Class<? extends Job> clazz, String jobName, String groupName){
        //构建任务，指定任务对象（HelloJob），调度器会去调度它的execute方法
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobName,groupName)
                .build();
        return jobDetail;
    }

    public static void addMapToJobDataMap(JobDetail jobDetail, String key, Object value){
        jobDetail.getJobDataMap().putIfAbsent(key, value);
    }

    public static void addAllMapToJobDataMap(JobDetail jobDetail, Map<String,Object> map){
        jobDetail.getJobDataMap().putAll(map);
    }

}
