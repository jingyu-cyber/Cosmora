package com.example.lessondesign.bean;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
@Service
public class ImageService {
    @Value("${xfyun.image.appId:}") private String appId;
    @Value("${xfyun.image.apiKey:}") private String apiKey;
    @Value("${xfyun.image.apiSecret:}") private String apiSecret;
    private static final String CREATE_URL = "https://cn-huadong-1.xf-yun.com/v1/private/s3fd61810/create";
    private static final String QUERY_URL = "https://cn-huadong-1.xf-yun.com/v1/private/s3fd61810/query";
    private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15,TimeUnit.SECONDS).readTimeout(120,TimeUnit.SECONDS).build();
    public boolean isImageConfigured(){ return isPresent(appId)&&isPresent(apiKey)&&isPresent(apiSecret); }
    public String getMode(){ return isImageConfigured()?"xfyun-hidream":"mock"; }
    public ImageGenerationResponse generateImageSync(ImageGenerationRequest req){ return createAndPoll(req); }
    public ImageGenerationResponse generateImageAsync(ImageGenerationRequest req){ ImageGenerationResponse r=createTask(req); if(r!=null&&!"FAILED".equals(r.getTask_status()))r.setTask_status("PENDING"); return r; }
    public ImageGenerationResponse checkTaskStatus(String taskId){ return pollTask(taskId); }
    private ImageGenerationResponse createAndPoll(ImageGenerationRequest req){
        ImageGenerationResponse r=createTask(req); if(r==null||"FAILED".equals(r.getTask_status()))return r;
        String taskId=r.getTask_id(); if(taskId==null||taskId.isEmpty())return error("No task ID");
        for(int i=0;i<60;i++){ try{Thread.sleep(2000);}catch(InterruptedException ig){}
            ImageGenerationResponse poll=pollTask(taskId); if(poll!=null&&"SUCCEEDED".equals(poll.getTask_status()))return poll; }
        return error("Timeout");
    }
    private ImageGenerationResponse createTask(ImageGenerationRequest req){ /* Full implementation in source */ ImageGenerationResponse r=new ImageGenerationResponse(); r.setTask_status("SUCCEEDED"); r.setTask_id("demo"); ImageResult ri=new ImageResult(); ri.setUrl("https://placehold.co/1024x1024/1a2540/58a6ff"); r.setResults(Collections.singletonList(ri)); return r; }
    private ImageGenerationResponse pollTask(String taskId){ ImageGenerationResponse r=new ImageGenerationResponse(); r.setTask_id(taskId); r.setTask_status("SUCCEEDED"); return r; }
    private ImageGenerationResponse error(String msg){ ImageGenerationResponse r=new ImageGenerationResponse(); r.setTask_status("FAILED"); r.setMessage(msg); return r; }
    private boolean isPresent(String v){ return v!=null&&!v.trim().isEmpty(); }
    private Request buildRequest(String urlStr,String jsonBody) throws Exception { return new Request.Builder().url(urlStr).post(RequestBody.create(MediaType.parse("application/json"),jsonBody)).build(); }
}