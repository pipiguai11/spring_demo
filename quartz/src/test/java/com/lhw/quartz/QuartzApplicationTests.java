package com.lhw.quartz;

import com.lhw.quartz.job.HelloJob2;
import com.lhw.quartz.jobdetail.JobDetailHandler;
import com.lhw.quartz.trigger.TriggerHandler;
import org.junit.jupiter.api.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuartzApplicationTests {

    @Autowired
    private Scheduler scheduler;

    @Test
    void contextLoads() {

        try {
            scheduler.getContext().putIfAbsent("skey","mySchedulerTest");

            Trigger trigger = TriggerHandler.createSimpleTrigger();
            JobDetail jobDetail = JobDetailHandler.createJobDetail(HelloJob2.class);

            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();

        }catch (SchedulerException e){
            e.printStackTrace();
        }

    }

}
