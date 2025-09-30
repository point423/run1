package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(id,username,road,utime) VALUES(#{id},#{username},#{road},#{utime})")
    void insertuser(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(Long id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User getUserByUsername(String username);
}
