package com.example.lessondesign.controller;
import com.example.lessondesign.bean.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.*;
@RestController
@RequestMapping("/api")
public class PPTController {
    @Autowired private ApiClient apiClient;
    @Value("${xfyun.ppt.appId:}") private String appId;
    @Value("${xfyun.ppt.apiSecret:}") private String apiSecret;
    @GetMapping("/ppt/status") public ResponseEntity<Map<String,Object>> status() {
        Map<String,Object> r=new HashMap<>();boolean c=appId!=null&&!appId.isEmpty()&&apiSecret!=null&&!apiSecret.isEmpty();r.put("service","iFLYTEK PPT");r.put("configured",c);r.put("mode",c?"xfyun-ppt":"mock");return ResponseEntity.ok(r);
    }
    @PostMapping("/generate-ppt") public ResponseEntity<Map<String,Object>> generatePPT(@RequestBody Map<String,Object> req) {
        Map<String,Object> r=new HashMap<>();
        try {
            if(appId==null||appId.isEmpty()){ r.put("code",400);r.put("message","PPT API key not configured");return ResponseEntity.badRequest().body(r); }
            long ts=System.currentTimeMillis()/1000;
            ApiAuthAlgorithm auth=new ApiAuthAlgorithm();
            String sig=auth.getSignature(appId,apiSecret,ts);
            File temp=File.createTempFile("ppt-",".txt");
            java.io.FileUtils.writeStringToFile(temp,(String)req.get("topic"),"UTF-8");
            String result=apiClient.create(appId,String.valueOf(ts),sig,temp);
            CreateResponse cr=com.alibaba.fastjson.JSON.parseObject(result,CreateResponse.class);
            if(cr.getData()!=null&&cr.getData().getSid()!=null){ r.put("code",0);r.put("message","PPT task submitted");r.put("data",Collections.singletonMap("sid",cr.getData().getSid())); }
            else{ r.put("code",cr.getCode());r.put("message","PPT failed: "+(cr.getDesc()!=null?cr.getDesc():"Unknown")); }
            temp.delete();
        }catch(Exception e){ r.put("code",500);r.put("message","PPT error: "+e.getMessage()); }
        return ResponseEntity.ok(r);
    }
}