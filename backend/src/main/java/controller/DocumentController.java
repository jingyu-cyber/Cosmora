package com.example.lessondesign.controller;
import com.example.lessondesign.service.LearningAgentService;
import com.example.lessondesign.dto.learning.LearningAgentRequest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.Tika;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins="*")
public class DocumentController {
    @Value("${upload.dir:./uploads}") private String uploadDir;
    @Autowired private LearningAgentService learningAgentService;
    private final ConcurrentHashMap<String,String> fileIdToPath=new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String,String> summaries=new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String,String> chatResults=new ConcurrentHashMap<>();
    @PostMapping("/upload") public ResponseEntity<Map<String,Object>> uploadDocument(@RequestParam("file") MultipartFile file) {
        Map<String,Object> r=new HashMap<>();
        try {
            File dir=new File(uploadDir);if(!dir.exists())dir.mkdirs();
            String fileId=UUID.randomUUID().toString();
            Path path=Paths.get(uploadDir,fileId+"_"+file.getOriginalFilename());
            Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
            fileIdToPath.put(fileId,path.toString());
            r.put("success",true);r.put("fileId",fileId);r.put("fileName",file.getOriginalFilename());
            return ResponseEntity.ok(r);
        }catch(Exception e){ r.put("success",false);r.put("message",e.getMessage());return ResponseEntity.status(500).body(r); }
    }
    @PostMapping("/summary/start/{fileId}") public ResponseEntity<Map<String,Object>> startSummary(@PathVariable String fileId) {
        Map<String,Object> r=new HashMap<>();
        try {
            String p=fileIdToPath.get(fileId);Tika t=new Tika();
            String text=t.parseToString(new File(p));if(text.length()>120000)text=text.substring(0,120000);
            LearningAgentRequest req=new LearningAgentRequest();req.setAgentType("training");
            req.setMessage("Summarize in 200 words:\n"+text);
            String summary=learningAgentService.chat(req).getContent();
            summaries.put(fileId,summary!=null?summary:"Summary unavailable");
            r.put("success",true);r.put("summary",summaries.get(fileId));return ResponseEntity.ok(r);
        }catch(Exception e){ r.put("success",false);r.put("message",e.getMessage());return ResponseEntity.status(500).body(r); }
    }
    @GetMapping("/summary/{fileId}") public ResponseEntity<Map<String,Object>> getSummary(@PathVariable String fileId) {
        Map<String,Object> r=new HashMap<>();r.put("success",true);r.put("summary",summaries.getOrDefault(fileId,"Processing..."));
        return ResponseEntity.ok(r);
    }
    @PostMapping("/chat") public ResponseEntity<Map<String,Object>> chatWithDoc(@RequestBody Map<String,Object> body) {
        Map<String,Object> r=new HashMap<>();String rid=UUID.randomUUID().toString();
        r.put("requestId",rid);
        try {
            String fileId=(String)((List)body.get("fileIds")).get(0);
            String q=(String)((List)body.get("messages")).get(0);
            String p=fileIdToPath.get(fileId);Tika t=new Tika();
            String text=t.parseToString(new File(p));if(text.length()>80000)text=text.substring(0,80000);
            LearningAgentRequest req=new LearningAgentRequest();req.setAgentType("training");
            req.setMessage("Based on this document:\n"+text+"\n\nQuestion: "+q);
            chatResults.put(rid,learningAgentService.chat(req).getContent());
        }catch(Exception e){ chatResults.put(rid,"Error: "+e.getMessage()); }
        return ResponseEntity.ok(r);
    }
    @GetMapping("/chat/result/{rid}") public ResponseEntity<Map<String,Object>> getChatResult(@PathVariable String rid) {
        Map<String,Object> r=new HashMap<>();r.put("completed",chatResults.containsKey(rid));r.put("result",chatResults.get(rid));return ResponseEntity.ok(r);
    }
}