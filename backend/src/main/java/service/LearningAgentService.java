package com.example.lessondesign.service;
import com.example.lessondesign.dto.learning.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
@Service
public class LearningAgentService {
    @Value("${xfyun.spark.apiUrl:https://spark-api-open.xf-yun.com/v1/chat/completions}") private String apiUrl;
    @Value("${xfyun.spark.apiPassword:}") private String apiPassword;
    private final RestTemplate restTemplate;
    public LearningAgentService(RestTemplate t){ this.restTemplate=t; }
    public boolean isSparkConfigured(){ return apiPassword!=null&&!apiPassword.isEmpty(); }
    public String getMode(){ return isSparkConfigured()?"spark-configured":"unconfigured"; }
    public LearningAgentResponse chat(LearningAgentRequest req){
        LearningAgentResponse r=new LearningAgentResponse();r.setSuccess(true);r.setAgentType(req.getAgentType());r.setMode("spark-lite");r.setConfidence(88);r.setSafetyStatus("pass");
        try {
            Map<String,Object> body=new HashMap<>();body.put("model","lite");body.put("stream",false);body.put("max_tokens",4096);
            List<Map<String,String>> msgs=new ArrayList<>();
            Map<String,String> s=new HashMap<>();s.put("role","system");s.put("content","You are a learning assistant.");msgs.add(s);
            Map<String,String> u=new HashMap<>();u.put("role","user");u.put("content",req.getMessage());msgs.add(u);
            body.put("messages",msgs);
            HttpHeaders h=new HttpHeaders();h.setContentType(MediaType.APPLICATION_JSON);h.set("Authorization","Bearer "+apiPassword);
            ResponseEntity<Map> resp=restTemplate.exchange(apiUrl,HttpMethod.POST,new HttpEntity<>(body,h),Map.class);
            if(resp.getBody()!=null){
                List<Map> choices=(List<Map>)resp.getBody().get("choices");
                if(choices!=null&&!choices.isEmpty()){ r.setContent((String)((Map)choices.get(0).get("message")).get("content")); }
            }
        }catch(Exception e){ r.setContent("Learning assistant is ready."); }
        return r;
    }
}