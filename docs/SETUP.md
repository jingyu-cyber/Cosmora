# Qiongjing Cosmora Setup Guide

## Prerequisites
- Java 8+ & Maven
- Node.js 16+
- MySQL 8.0
- iFLYTEK Developer Account

## Quick Start
1. Import database: mysql < database/teacher_course.sql
2. Start backend: cd backend && mvn spring-boot:run
3. Start frontend: cd frontend && npm install && npm run serve
4. Open http://localhost:8081

## API Configuration
Set environment variables for iFLYTEK services:
- SPARK_API_PASSWORD
- XFYUN_IMAGE_APPID/APIKEY/APISECRET
- XFYUN_PPT_APPID/APISECRET
- VOICE_APPID/APIKEY/APISECRET