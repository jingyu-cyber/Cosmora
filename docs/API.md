# API Documentation

## Backend APIs (port 9070)

### Image Generation
- GET /api/image/status
- POST /api/image/generate

### Learning Agent 
- GET /api/learning-agent/status
- POST /api/learning-agent/chat

### Voice Service
- GET /api/voice/status
- POST /api/voice/tts

### PPT Generation
- GET /api/ppt/status
- POST /api/generate-ppt

### Document Reading
- POST /api/document/upload
- POST /api/document/summary/start/:id
- GET /api/document/summary/:id
- POST /api/document/chat

### User Management
- POST /api/users/register
- POST /api/users/login
- POST /api/users/update
- GET /api/users/profile/:id