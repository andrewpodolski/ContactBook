package com.andrew;

import com.andrew.commands.impl.EmailJob;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EmailScheduler implements ServletContextListener {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    //time - 23:32
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            JobDetail job = JobBuilder.newJob(EmailJob.class).withIdentity("job", "group").build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger", "group")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 32 23 * * ?"))
                    .build();
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            scheduler.shutdown();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
