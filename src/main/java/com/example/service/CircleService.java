package com.example.service;

import com.example.entity.Circle;

public interface CircleService {
    public String savecircle(Circle circle);

    Circle getcirclebyid(Long id);

    boolean updatecircle(Circle circle);

    boolean deletecircle(Long id);

    boolean addmembertocircle(Long circleid, Long userid);

    boolean deletemembertocircle(Long circleid, Long userid);
}
