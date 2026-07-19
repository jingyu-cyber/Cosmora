@echo off
:: Qiongjing Cosmora Backend Startup
:: Copy this to start-backend.bat and fill in your API keys
set SPARK_API_PASSWORD=your_key_here
set SPARK_APP_ID=your_appid
set XFYUN_IMAGE_APPID=your_appid
set XFYUN_IMAGE_APIKEY=your_key
set XFYUN_IMAGE_APISECRET=your_secret
set XFYUN_PPT_APPID=your_appid
set XFYUN_PPT_APISECRET=your_secret
cd backend
java -jar target/LessonDesign-0.0.1-SNAPSHOT.jar