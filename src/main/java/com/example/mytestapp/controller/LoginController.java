package com.example.mytestapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mytestapp.entity.User;
import com.example.mytestapp.entity.UserLogin;
import com.example.mytestapp.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    /**
     * Function that check the user email and password
     * returning the User instance from MySQL database
     * **/
    @PostMapping("/userlogin")
    public User login(@RequestBody UserLogin userLogin) {
        String sha256pwd = DigestUtils.sha256Hex(userLogin.getPassword());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userLogin.getEmail()).eq("password",sha256pwd);
        return userMapper.selectOne(queryWrapper);
    }
}
