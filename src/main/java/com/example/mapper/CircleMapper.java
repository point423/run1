package com.example.mapper;

import com.example.entity.Circle;
import com.example.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CircleMapper {
    @Insert("INSERT INTO circle(id,name,ownerid,description,joinrule,maxusers) VALUES(#{id},#{name},#{ownerid},#{description},#{joinrule},#{maxusers})")
    void Insertcircle(Circle circle);

    @Select("SELECT * FROM circle WHERE id = #{id}")
    Circle getCircleById(Long id);

    @Select("SELECT * FROM circle WHERE name = #{name}")
    Circle getCircleByname(String name);

    @Update("UPDATE circle SET name = #{name}, description = #{description} WHERE id = #{id}")
    int update(Circle circle);

    @Delete("DELETE FROM circle WHERE id = #{id}")
    int deleteById(Long id);

    @Insert("INSERT INTO circle_member(circle_id, user_id) VALUES(#{circleId}, #{userId})")
    int addMember(@Param("circleId") Long circleId, @Param("userId") Long userId);

    @Delete("DELETE FROM circle_member WHERE circle_id = #{circleId} AND user_id = #{userId}")
    int removeMember(@Param("circleId") Long circleId, @Param("userId") Long userId);
}
