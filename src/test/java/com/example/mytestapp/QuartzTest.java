package com.example.mytestapp;

import com.example.mytestapp.job.DatabaseJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;


public class QuartzTest {

    public static void main(String[] args) throws SchedulerException {
        Calendar calendar=Calendar.getInstance();
        calendar.set(2022, Calendar.APRIL, 26, 13, 10,0);  //年月日  也可以具体到时分秒如calendar.set(2015, 10, 12,11,32,52);
        Date date=calendar.getTime();//date就是你需要的时间

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail backJobDetail = JobBuilder.newJob(DatabaseJob.class)
                .withIdentity("database backup", "routine")
                .build();
        Trigger dailyTrigger = TriggerBuilder.newTrigger()
                .withIdentity("daily trigger", "routine")
                .startAt(date)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(5))
                .build();

        scheduler.scheduleJob(backJobDetail, dailyTrigger);
        scheduler.start();
    }
}
