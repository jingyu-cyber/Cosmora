package com.example.lessondesign.controller;
import com.example.lessondesign.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/voice")
public class VoiceController {
    @Autowired private VoiceService voiceService;
    @GetMapping("/status") public ResponseEntity<Map<String,Object>> status() {
        Map<String,Object> r=new HashMap<>();r.put("service","iFLYTEK Voice");r.put("configured",voiceService.isConfigured());r.put("mode",voiceService.getMode());r.put("capabilities",new String[]{"tts","iat","ise","lfasr"});return ResponseEntity.ok(r);
    }
    @PostMapping("/tts") public ResponseEntity<Map<String,Object>> tts(@RequestBody Map<String,String> body) {
        Map<String,Object> r=new HashMap<>();r.put("success",true);r.put("audioBase64",voiceService.tts(body.get("text")));return ResponseEntity.ok(r);
    }
    @PostMapping("/iat") public ResponseEntity<Map<String,Object>> iat(@RequestBody Map<String,String> body) {
        Map<String,Object> r=new HashMap<>();r.put("success",true);r.put("text",voiceService.iat(body.get("audio")));return ResponseEntity.ok(r);
    }
}