package com.example.service;

import com.example.entity.Community;

public interface CommunityService {
    public String savecommunity(Community community);
    public String deletecommunity(Long id);
}
