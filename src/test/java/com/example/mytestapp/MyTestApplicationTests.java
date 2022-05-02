package com.example.mytestapp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mytestapp.job.DatabaseJob;
import com.example.mytestapp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.mytestapp.entity.User;
import redis.clients.jedis.Jedis;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MyTestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInsert(){
        User testUser = new User();
        testUser.setEmail("likelike101101@gmail.com");
        testUser.setName("Ke");
        testUser.setPassword("123456");
        int result = userMapper.insert(testUser);
        System.out.println(result + " rows have been affected");
        System.out.println("The test user id is " + testUser.getId());
    }

    @Test
    void testDelete(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", "Ke");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result + " rows have been affected. ");

    }

    @Test
    void testSearch(){
        System.out.println("Please enter the name: ");
        String name = "keli";
        System.out.println(name);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", name);
        User testUser = userMapper.selectOne(queryWrapper);
        System.out.println(name + "'s email is " + testUser.getEmail());

    }

    @Test
    void testUpdate(){
        System.out.println("Please enter the name: ");
        String name = "keli";
        System.out.println(name);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", name);
        User testUser = userMapper.selectOne(queryWrapper);
        System.out.println("Please enter the updated email: ");
        String newEmail = "keli2023@bu.edu";
        testUser.setEmail(newEmail);
        int result = userMapper.updateById(testUser);
        System.out.println("success! " + result + " rows have been affected. ");
    }

    @Test
    void redisTest(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("test", "success");
        String result = jedis.get("test");
        System.out.println(result);
        if (result.equals("success")){
            System.out.println("Redis read and write success");
        }else{
            System.out.println("failed");
        }
    }

    @Test
    void quartzTest() throws SchedulerException {
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
