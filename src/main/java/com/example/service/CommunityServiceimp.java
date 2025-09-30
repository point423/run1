package com.example.service;

import com.example.entity.Community;
import com.example.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunityServiceimp implements CommunityService{
    private final CommunityMapper communityMapper;

    @Autowired
    public CommunityServiceimp(CommunityMapper communityMapper){
        this.communityMapper = communityMapper;
    }

    @Transactional
    @Override
    public String savecommunity(Community community){


        // 检查 ID 是否存在
        if (community.getId() != null && communityMapper.getCommunityById(community.getId()) != null) {
            System.out.println("ID 已存在，插入失败");
            return "ID 已存在";
        }

        // 检查用户名是否存在
        if (communityMapper.getCommunityByname(community.getName()) != null) {
            System.out.println("用户名已存在，插入失败");
            return "用户名已存在";
        }

        // 插入数据
        communityMapper.Insertcommunity(community);



        return "插入community成功";
    }

    @Override
    @Transactional
    public String deletecommunity(Long id){
        System.out.println("开始删除community，ID: " + id);

        // 检查 ID 是否存在
        if (communityMapper.getCommunityById(id) == null) {
            System.out.println("ID 不存在，删除失败");
            return "ID 不存在";
        }

        int affectedRows = communityMapper.deleteById(id);
        System.out.println("删除操作影响行数: " + affectedRows);

        // 双重验证：存在性检查+实际删除行数
        if(affectedRows == 0) {
            System.out.println("删除失败（无记录被影响）");
            return "删除失败（无记录被影响）";
        }

        return "删除community成功";
    }
}