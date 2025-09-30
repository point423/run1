package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private String username;
    private String road;
    private  Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date utime;

    public User() {
    }

    // 有参构造函数（可选，若有则无参构造函数不能省略）
    public User(Long id, String username, String road, Date utime) {
        this.id = id;
        this.username = username;
        this.road = road;
        this.utime = utime;
    }
    public Long getId() {
        return id;
    }

    public Date getUtime() {
        return utime;
    }

    public String getUsername() {
        return username;
    }

    public String getRoad() {
        return road;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setUtime(Date time) {
        this.utime = time;
    }
}
