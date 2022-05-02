package com.example.mytestapp.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dataString = dateFormat.format(new Date());
        System.out.println("----------------------------------------");
        System.out.println("Backing up Databases: "+ dataString);
        System.out.println("----------------------------------------");
    }
}
