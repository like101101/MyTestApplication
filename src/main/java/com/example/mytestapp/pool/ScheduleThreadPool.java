package com.example.mytestapp.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {

    public void runPool(){
        ScheduledExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 1; i++){
            System.out.println("Thread start time = "+ new Date());
            scheduleThreadPool.scheduleAtFixedRate(new Task("task-"+i),5,6, TimeUnit.SECONDS);
        }

    }
}

class Task implements Runnable{

    private String name;

    public Task (String name){
        this.name = name;
    }

    @Override
    public void run() {
        try{
            System.out.println("name="+name+", start time = "+ new Date());
            Thread.sleep(3000);
            System.out.println("name="+name+", end time = "+ new Date());
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
