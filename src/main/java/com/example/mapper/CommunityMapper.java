package com.example.mapper;

import com.example.entity.Community;
import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommunityMapper {
    @Insert("INSERT INTO community(id,name,ownerid,note) VALUES(#{id},#{name},#{ownerid},#{note})")
    void Insertcommunity(Community community);
    @Delete("DELETE FROM community WHERE id = #{id}")
    int deleteById( Long id);
    @Select("SELECT * FROM community WHERE id = #{id}")
    Community getCommunityById(Long id);

    @Select("SELECT * FROM community WHERE name = #{name}")
    Community getCommunityByname(String name);
}
