package com.example.lessondesign.controller;
import com.example.lessondesign.dto.learning.LearningAgentRequest;
import com.example.lessondesign.dto.learning.LearningAgentResponse;
import com.example.lessondesign.service.LearningAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/learning-agent")
public class LearningAgentController {
    @Autowired private LearningAgentService service;
    @GetMapping("/status") public ResponseEntity<Map<String,Object>> status() {
        Map<String,Object> r=new HashMap<>();r.put("service","Qiongjing Cosmora Learning Agent");r.put("sparkConfigured",service.isSparkConfigured());r.put("mode",service.getMode());r.put("model","lite");r.put("availableAgents",Arrays.asList("portrait","diagnosis","resource","path","training","evaluation"));return ResponseEntity.ok(r);
    }
    @PostMapping("/chat") public ResponseEntity<LearningAgentResponse> chat(@RequestBody LearningAgentRequest req) { return ResponseEntity.ok(service.chat(req)); }
}