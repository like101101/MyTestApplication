package com.example.mytestapp.mapper;

import com.example.mytestapp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
