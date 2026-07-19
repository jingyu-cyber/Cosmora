# CLAUDE.md - Qiongjing Cosmora

## Project Overview
Qiongjing Cosmora is an AI-powered smart education platform with multi-agent learning capabilities.

## Tech Stack
- Frontend: Vue 2 + Element UI + ECharts (port 8081)
- Backend: Spring Boot + MyBatis + MySQL (port 9070)
- AI: iFLYTEK Spark / HiDream / ChatDoc / Voice

## Commands
```bash
# Frontend
cd frontend && npm run serve

# Backend  
cd backend && mvn spring-boot:run
```

## Architecture
7 pages: FullChain, LearningPath, KnowledgeQuiz, AiTutor, ResourceReading, ResourceGeneration
6 AI APIs: Spark LLM, HiDream Image, PPT, Voice TTS/IAT, ChatDoc, LearningAgent