package com.example.lessondesign.bean;
import lombok.Data;
import java.util.*;
@Data
public class ImageGenerationResponse { private String request_id; private String task_id; private String task_status; private List<ImageResult> results; private Map<String,Object> task_metrics; private Map<String,Object> usage; private String message; }