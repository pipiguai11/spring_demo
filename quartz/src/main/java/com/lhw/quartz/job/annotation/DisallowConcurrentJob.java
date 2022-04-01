package com.lhw.quartz.job.annotation;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：linhw
 * @date ：22.4.1 14:12
 * @description：不允许并行运行的Job
 *
 *      在Job实现类上添加@DisallowConcurrentExecution注解标识这个Job不允许多个实例同时运行
 *
 * @modified By：
 */
@DisallowConcurrentExecution
public class DisallowConcurrentJob implements Job {

    private final Logger log = LoggerFactory.getLogger(DisallowConcurrentJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("【" + context.getJobDetail().getKey().getName() + "】任务被调度了。。。。。。。。。。。。。。。。");
        log.info("当前触发器为【" + context.getTrigger().getKey().getName() + "】");
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("【" + context.getJobDetail().getKey().getName() + "】执行结束");
    }
}
