package com.example.mytestapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mytestapp.entity.User;
import com.example.mytestapp.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/set")
    public void set(@RequestBody User user) {
        String pwd = user.getPassword();
        user.setPassword(DigestUtils.sha256Hex(pwd));
        userMapper.insert(user);
    }

    @GetMapping("/get/{email}")
    @ApiOperation(value = "Find User by email",
            notes = "Provide the email of user to look up in the database",
            response = User.class
    )
    public User getByEmail(@PathVariable("email") String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectOne(queryWrapper);
    }
}

