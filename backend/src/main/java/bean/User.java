package com.example.lessondesign.bean;
import lombok.Data;
import java.sql.Timestamp;
@Data
public class User { private String userId; private String username; private String password; private String role; private String phone; private String title; private String email; private String gender; private String research; private String targetDirection; private Integer schoolId; private String schoolName; private String schoolLocation; private String content; private Timestamp createdAt; }