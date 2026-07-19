package com.example.lessondesign.controller;
import com.example.lessondesign.bean.SparkModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/qianfan")
public class QianfanController {
    @Autowired private SparkModel sparkModel;
    @PostMapping("/query") public ResponseEntity<String> query(@RequestBody String q) { return ResponseEntity.ok(sparkModel.chat("You are a learning assistant.",q)); }
}