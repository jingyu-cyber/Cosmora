package com.example.lessondesign.controller;
import com.example.lessondesign.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired private ImageService imageService;
    @GetMapping("/status") public ResponseEntity<Map<String,Object>> status() {
        Map<String,Object> r=new HashMap<>();
        r.put("service","iFLYTEK HiDream");
        r.put("configured",imageService.isImageConfigured());
        r.put("mode",imageService.getMode());
        return ResponseEntity.ok(r);
    }
    @PostMapping("/generate") public ResponseEntity<ImageGenerationResponse> generateImage(@RequestBody ImageGenerationRequest req) {
        try { return ResponseEntity.ok(req.isAsync()?imageService.generateImageAsync(req):imageService.generateImageSync(req)); }
        catch(Exception e){ ImageGenerationResponse r=new ImageGenerationResponse();r.setTask_status("FAILED");r.setMessage(e.getMessage());return ResponseEntity.ok(r); }
    }
    @GetMapping("/task/{taskId}") public ResponseEntity<ImageGenerationResponse> checkTask(@PathVariable String taskId) {
        return ResponseEntity.ok(imageService.checkTaskStatus(taskId));
    }
}