package com.example.lessondesign.bean;
import com.alibaba.fastjson.*;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
@Service
public class SparkModel {
    @Value("${xfyun.spark.apiUrl:https://spark-api-open.xf-yun.com/v1/chat/completions}") private String apiUrl;
    @Value("${xfyun.spark.apiPassword:}") private String apiPassword;
    @Value("${xfyun.spark.appId:}") private String appId;
    @Value("${xfyun.spark.model:lite}") private String model;
    private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15,TimeUnit.SECONDS).readTimeout(120,TimeUnit.SECONDS).build();
    public boolean isConfigured(){ return apiPassword!=null&&!apiPassword.isEmpty()&&appId!=null&&!appId.isEmpty(); }
    public String getMode(){ return isConfigured()?"xfyun-spark":"unconfigured"; }
    public String chat(String system,String user){
        if(!isConfigured()) return "[Spark not configured]";
        try {
            JSONObject body=new JSONObject();body.put("model","lite");
            JSONArray msgs=new JSONArray();
            if(system!=null&&!system.isEmpty()){ JSONObject s=new JSONObject();s.put("role","system");s.put("content",system);msgs.add(s); }
            JSONObject u=new JSONObject();u.put("role","user");u.put("content",user);msgs.add(u);
            body.put("messages",msgs);body.put("temperature",0.7);body.put("max_tokens",2048);
            Request req=new Request.Builder().url(apiUrl).post(RequestBody.create(MediaType.parse("application/json"),body.toJSONString())).addHeader("Authorization","Bearer "+apiPassword).build();
            try(Response resp=client.newCall(req).execute()){
                String respBody=resp.body().string();
                if(resp.isSuccessful()){
                    JSONObject obj=JSON.parseObject(respBody);
                    return obj.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
                }
                return "[Spark error "+resp.code()+"]";
            }
        }catch(IOException e){ return "[Spark IO error]"; }
    }
}