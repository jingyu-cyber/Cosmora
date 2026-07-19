package com.example.lessondesign.dto.learning;
import lombok.Data;
@Data
public class LearningAgentResponse { private boolean success; private String content; private String agentType; private String mode; private Integer confidence; private String safetyStatus; private String nextAction; }