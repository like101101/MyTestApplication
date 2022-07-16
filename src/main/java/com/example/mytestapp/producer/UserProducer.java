package com.example.mytestapp.producer;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mytestapp.entity.User;
import com.example.mytestapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserProducer {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KafkaTemplate<String, User> UserTemplate;
    private static final String USERTOPIC = "Users";

    @Autowired
    private KafkaTemplate<String, String> HelloTemplate;

    private static final String HELLOTOPIC = "HelloMessages";



    @GetMapping("/send/hello/{email}")
    public String postHello(@PathVariable("email") final String email){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User postUser = userMapper.selectOne(queryWrapper);
        HelloTemplate.send(HELLOTOPIC, "Hello! Sent by "+postUser.getName());
        return "Hello message successfully";
    }

    @GetMapping("/send/user/{name}")
    public String postUser(@PathVariable("name") final String name){
        User postUser = new User();
        postUser.setName(name);
        UserTemplate.send(USERTOPIC, postUser);
        return "User " + name + " send successfully";
    }
}
