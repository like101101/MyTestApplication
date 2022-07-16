package com.example.mytestapp.controller;

import com.example.mytestapp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

@RestController
@RequestMapping("/student")
public class StudentHandler {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    /**
     * Accessing the redis server
     * **/
    public void set(@RequestBody Student student){
        redisTemplate.opsForValue().set(student.getName(), student);
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key){
        return (Student) redisTemplate.opsForValue().get(key);
    }

    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);

    }
}
