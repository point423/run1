package com.example.controller;

import com.example.entity.Circle;
import com.example.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/circles")
public class CircleController {
    private final CircleService circleService;
    @Autowired
    public CircleController(CircleService circleService){
        this.circleService=circleService;
    }
    @PostMapping
    public ResponseEntity<String> savecircle(@RequestBody Circle circle){
        String result= circleService.savecircle(circle);
        if(result.equals("插入circle成功")){
            return ResponseEntity.ok(result);
        }else {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Circle> getcircle(@PathVariable Long id){
        Circle circle=circleService.getcirclebyid(id);
        return circle!=null?ResponseEntity.ok(circle):ResponseEntity.notFound().build();
    }
    @PutMapping
    public  ResponseEntity<String> updatecircle(@RequestBody Circle circle){
        boolean result=circleService.updatecircle(circle);
        return result?ResponseEntity.ok("圈子更新成功"):ResponseEntity.badRequest().body("更新失败");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecircle(@PathVariable Long id){
        boolean result=circleService.deletecircle(id);
        return  result?ResponseEntity.ok("删除成功"):ResponseEntity.badRequest().body("删除失败");
    }
    @PostMapping("/{circleid}/members/{userid}")
    public ResponseEntity<String> addmember(@PathVariable Long circleid,@PathVariable Long userid){
        boolean result=circleService.addmembertocircle(circleid,userid);
        return result?ResponseEntity.ok("成员添加成功"):ResponseEntity.badRequest().body("成员添加失败");

    }
    @DeleteMapping("/{circleId}/members/{userId}")
    public ResponseEntity<String> removeMember(@PathVariable Long circleId, @PathVariable Long userId) {
        boolean result = circleService.deletemembertocircle(circleId, userId);
        return result ? ResponseEntity.ok("成员移除成功") : ResponseEntity.badRequest().body("成员移除失败");
    }
}
