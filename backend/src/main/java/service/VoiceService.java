package com.example.lessondesign.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class VoiceService {
    @Value("${xfyun.voice.appId:}") private String appId;
    @Value("${xfyun.voice.apiKey:}") private String apiKey;
    @Value("${xfyun.voice.apiSecret:}") private String apiSecret;
    public boolean isConfigured(){ return appId!=null&&!appId.isEmpty()&&apiKey!=null&&!apiKey.isEmpty(); }
    public String getMode(){ return isConfigured()?"xfyun-configured":"mock"; }
    public String tts(String text){ return isConfigured()?"[TTS audio]":""; }
    public String iat(String audio){ return isConfigured()?"[IAT text]":""; }
}