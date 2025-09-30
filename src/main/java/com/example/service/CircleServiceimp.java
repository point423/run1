package com.example.service;

import com.example.entity.Circle;
import com.example.mapper.CircleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CircleServiceimp implements CircleService {
    private final CircleMapper circleMapper;
    @Autowired
    public CircleServiceimp(CircleMapper circleMapper){
        this.circleMapper=circleMapper;
    }
    @Transactional
    @Override
    public String savecircle(Circle circle){
        // 检查 ID 是否存在
        if (circle.getId() != null && circleMapper.getCircleById(circle.getId()) != null) {
            return "ID 已存在";
        }

        // 检查用户名是否存在
        if (circleMapper.getCircleByname(circle.getName()) != null) {
            return "用户名已存在";
        }
        circleMapper.Insertcircle(circle);
        return "插入circle成功";
    }
    @Override
    public Circle getcirclebyid(Long id){
        return circleMapper.getCircleById(id);
    }
    @Override
    public boolean updatecircle(Circle circle){
        return circleMapper.update(circle)>0;
    }
    @Override
    public boolean deletecircle(Long id){
        return  circleMapper.deleteById(id)>0;
    }
    @Override
    public boolean addmembertocircle(Long circleid, Long userid){
        return circleMapper.addMember(circleid,userid)>0;
    }
    @Override
    public boolean deletemembertocircle(Long circleid, Long userid){
        return circleMapper.removeMember(circleid,userid)>0;
    }

}
