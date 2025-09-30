package com.example.controller;

import com.example.entity.Community;
import com.example.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/communitys")
public class CommunityController {
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }

    @PostMapping
    public ResponseEntity<String> savecommunity(@RequestBody Community community){


        String result = communityService.savecommunity(community);

        if(result.equals("插入community成功")){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecommunity(@PathVariable Long id){
        String result = communityService.deletecommunity(id);
        if(result.equals("删除community成功")){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
    }
}