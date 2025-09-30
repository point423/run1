package com.example;

import com.example.entity.Community;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class rapplication {
    public static void main(String[] args){
        SpringApplication.run(rapplication.class,args);
    }
}
