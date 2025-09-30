package com.example.entity;

import java.util.Date;
import java.util.List;

public class Circle {
    private Long id;
    private String name;
    private Long ownerid;
    private String description;
    private int joinrule;//加入规则,0 = 自由加入，1 = 申请审核
    private int maxusers;//最大可容纳成员
    private List<Long> members;
    public Circle(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJoinrule(int joinrule) {
        this.joinrule = joinrule;
    }

    public void setOwnerid(Long ownerid) {
        this.ownerid = ownerid;
    }

    public void setMaxusers(int maxusers) {
        this.maxusers = maxusers;
    }

    public Long getOwnerid() {
        return ownerid;
    }

    public int getJoinrule() {
        return joinrule;
    }

    public int getMaxusers() {
        return maxusers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMembers(List<Long> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }


    public List<Long> getMembers() {
        return members;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
