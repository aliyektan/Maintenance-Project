package com.aliyektan.project.schedule.trigger;

import com.aliyektan.project.schedule.job.MailJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by yektan on 18.12.2017.
 * Job class'ımın trigger'ı hergün 00:00 da job class'ımı çalıştırmakta.
 */
public class MailCronTrigger {

    public static void main( String[] args ) throws Exception
    {
        JobDetail job = JobBuilder.newJob(MailJob.class)
                .withIdentity("mailTrigger", "group1").build();


        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("mailTrigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))
                .build();

        //schedule it
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }

}
