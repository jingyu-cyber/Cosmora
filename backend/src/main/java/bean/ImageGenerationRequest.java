package com.example.lessondesign.bean;
import lombok.Data;
@Data
public class ImageGenerationRequest { private String prompt; private String style; private String size; private boolean async; }