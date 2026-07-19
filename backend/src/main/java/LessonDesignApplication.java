package com.example.lessondesign;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.lessondesign.dao")
@SpringBootApplication
public class LessonDesignApplication {
    public static void main(String[] args) { SpringApplication.run(LessonDesignApplication.class, args); }
}