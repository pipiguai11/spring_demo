package com.lhw.quartz.job_factory;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：linhw
 * @date ：22.4.1 11:30
 * @description：我的自定义job初始化工厂
 * @modified By：
 */
public class MyJobFactory implements JobFactory {

    private final Logger log = LoggerFactory.getLogger(MyJobFactory.class);

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        log.info("准备初始化【" + jobClass.getName() + "】");

        Job newJob = null;
        try {
            newJob = jobClass.newInstance();
            log.info("初始化完成");
        }catch (Exception e) {
            log.error("初始化【" + jobClass.getName() + "】失败");
        }

        return newJob;
    }
}
