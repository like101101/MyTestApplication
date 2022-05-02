package com.example.mytestapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Date birthday;
    private Integer midterm;
}
