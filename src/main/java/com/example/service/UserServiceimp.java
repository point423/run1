package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserServiceimp implements UserService {
    private final UserMapper usermapper;

    @Autowired
   public UserServiceimp(UserMapper usermapper){
       this.usermapper=usermapper;
   }
   @Transactional
   @Override
    public String saveuser(User user){
       // 检查 ID 是否存在
       if (user.getId() != null && usermapper.getUserById(user.getId()) != null) {
           return "ID 已存在";
       }

       // 检查用户名是否存在
       if (usermapper.getUserByUsername(user.getUsername()) != null) {
           return "用户名已存在";
       }
        usermapper.insertuser(user);
       return "插入user成功";
   }
}
