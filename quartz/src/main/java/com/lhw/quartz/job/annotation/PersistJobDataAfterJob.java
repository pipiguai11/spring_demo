package com.lhw.quartz.job.annotation;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：linhw
 * @date ：22.4.1 14:18
 * @description：动态更新JobDataMap中的值的Job
 *
 *      原本每个Job实例都是在调度的时候重新实例化的，因此保存不了状态值
 *      此时可以用这个@PersistJobDataAfterExecution注解去标注，将上一个Job实例（其实就是JobDetail实例）的某个状态值更新到下一个Job中
 *      如这里的num值，会逐渐加1，都是基于上一个Job的。
 *          只需要在执行过程中put一次即可
 *
 *      在使用@PersistJobDataAfterExecution时，最好是和@DisallowConcurrentExecution一起用
 *      为了防止多线程并发时状态值不准确的问题。
 *
 *
 * @modified By：
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PersistJobDataAfterJob implements Job {

    private final Logger log = LoggerFactory.getLogger(PersistJobDataAfterJob.class);

    public static final String TEST_NUM = "num";
    public static final String TEST_NAME = "job_name";

    private int _count = 1;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        int num = jobDataMap.getInt(TEST_NUM);

        log.info("准备更新【" + jobDetail.getKey() + "】的JobDataMap，JobName为：" + jobDataMap.getString(TEST_NAME));
        log.info("当前num为：{}, _count为：{}", num, _count);
        jobDataMap.put(TEST_NUM, ++num);
        log.info("更新完毕");

        _count++;
    }
}
