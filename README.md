# 穹镜 Cosmora

> 🧠 **基于大模型的个性化资源生成与学习多智能体系统**

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![Vue](https://img.shields.io/badge/Vue-2.6-green.svg)](https://vuejs.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6-brightgreen.svg)](https://spring.io/)
[![iFLYTEK](https://img.shields.io/badge/AI-%E8%AE%AF%E9%A3%9E%E6%98%9F%E7%81%AB-purple.svg)](https://www.xfyun.cn/)

---

## 📖 项目简介

穹镜 Cosmora 是一个 **AI 驱动的智慧教学平台**，面向高校课程学习场景，将学习画像、知识问答、智能一对一辅导和效果评估串联为可演示的多智能体协作链路。系统整合了**科大讯飞**全线 AI 能力，为学生提供从"教材阅读 → 知识检测 → AI 辅导 → 效果评估 → 资源生成"的完整学习闭环。

---

## ✨ 功能展示

### 🏠 学习闭环 — 多智能体工作台
AI 能力状态一目了然，展示 4/4 项讯飞服务已配置，五步学习闭环流程引导，知识库覆盖 7 类资源方向。

### 📊 学习画像
8 维度学习能力雷达图 + 对话式画像构建，学习准备度、知识掌握度、薄弱点可视化。

### 🗺️ 学习路径
个性化学习资源推荐，结合学习方向、难度等级和技能画像，AI 路径 Agent 生成学习步骤。

### 📝 知识问答 — 三库系统
- **本地题库**：30 套练习、270+ 道选择题/判断题/简答题
- **错题库**：自动沉淀错误回答，含复盘建议
- **知识盲区库**：记录一对一辅导中暴露的学习盲区

### 👩‍🏫 智能一对一 — 依莉 AI 老师
- **辅导模式**：学生提问，AI 老师答疑、提示和引导
- **挑战模式**：AI 主动出题、追问和评分
- **Live2D 角色**：依莉（Yili）Live2D 虚拟形象实时交互
- **语音交互**：讯飞语音 TTS 朗读 + IAT 语音输入

### 📖 资源助读
- 5 大方向 × 9 本教材（人工智能/Python/机器学习/计算机基础/机械工程）
- 教材阅读进度追踪、重点标记、学习笔记
- 文档上传分析（支持 PDF/DOCX/TXT/MD）
- AI 摘要生成 + 文档问答

### 🎨 资源生成
- **图片生成**：讯飞 HiDream AI 文生图，5 种快速模板
- **图表生成**：ECharts 柱状图/折线图/饼图/散点图/雷达图
- **课件生成**：讯飞 PPT API + 本地 PPTX 生成

---

## 🏗 系统架构

```
┌──────────────────────────────────────────────┐
│           Vue2 前端 (Port 8081)                │
│  学习闭环 │ 学习画像 │ 学习路径 │ 知识问答      │
│  智能一对一 │ 资源助读 │ 资源生成              │
├──────────────────────────────────────────────┤
│      Spring Boot 后端 (Port 9070)              │
│  Controllers(11) │ Services(2) │ DAOs(9)      │
│  MyBatis Mappers(11) │ Beans(14)              │
├──────────────────────────────────────────────┤
│              MySQL 8.0 (Port 3306)             │
├──────────────────────────────────────────────┤
│            科大讯飞 AI 服务                      │
│  星火Spark │ HiDream图片 │ PPT │ ChatDoc │ 语音 │
└──────────────────────────────────────────────┘

Live2D 角色: 依莉(Yili) Cubism SDK 5
3D 大屏: Vue3 + Three.js (Port 5173)
AI 面试服务: Flask (Port 5333)
```

---

## 🚀 快速开始

### 环境
- Java 8+ & Maven 3.9+
- Node.js 16+
- MySQL 8.0
- [科大讯飞开发者账号](https://console.xfyun.cn)（免费）

### 1. 数据库
```bash
mysql -u root -p < database/teacher_course.sql
```

### 2. 后端
```bash
cd backend && mvn package -DskipTests
java -jar target/LessonDesign-0.0.1-SNAPSHOT.jar
```

### 3. 前端
```bash
cd frontend && npm install && npm run serve
```

### 4. 配置 AI 密钥
```bash
export SPARK_API_PASSWORD="your_key"
export SPARK_APP_ID="your_appid"
export XFYUN_IMAGE_APPID="your_appid"
export XFYUN_IMAGE_APIKEY="your_key"
export XFYUN_IMAGE_APISECRET="your_secret"
export XFYUN_PPT_APPID="your_appid"
export XFYUN_PPT_APISECRET="your_secret"
export VOICE_APP_ID="your_appid"
export VOICE_API_KEY="your_key"
export VOICE_API_SECRET="your_secret"
```

### 5. 访问
打开 **http://localhost:8081** → 点击"演示"按钮体验全部功能。

---

## 📡 API 状态

| 服务 | 状态 | 接口 |
|------|------|------|
| 图片生成 HiDream | ✅ configured | `POST /api/image/generate` |
| 星火大模型 | ✅ spark-configured | `POST /api/learning-agent/chat` |
| 语音 TTS/IAT | ✅ configured | `POST /api/voice/tts` |
| PPT 生成 | ✅ configured | `POST /api/generate-ppt` |
| 文档阅读 | ✅ configured | `POST /api/document/upload` |

---

## 📂 项目结构

```
Cosmora/
├── backend/          # Spring Boot (9070) - Java 控制器/服务/DAO
├── frontend/         # Vue2 (8081) - 7 页面/3 组件/路由/数据
├── database/         # MySQL 建表 & 种子数据
├── AI/               # Flask 面试服务 (5333) - Live2D/语音/表情分析
├── bigscreen/        # Vue3 3D 大屏 (5173) - Three.js 地图
├── docs/             # 安装指南 & API 文档
└── live2d/           # 依莉 Live2D 模型文件
```

---

## 🧩 技术栈

| 层 | 技术 |
|----|------|
| **前端** | Vue 2.6 · Element UI · ECharts 5 · Axios · DOMPurify |
| **后端** | Spring Boot 2.6 · MyBatis · MySQL 8.0 · OkHttp 4 · Apache Tika |
| **AI** | 讯飞星火 · HiDream · PPT API · ChatDoc · 语音 TTS/IAT |
| **Live2D** | Cubism SDK 5 · PixiJS 6（依莉虚拟教师角色）|
| **3D 大屏** | Vue 3 · Vite · Three.js · GSAP |
| **安全** | BCrypt · DOMPurify · 环境变量注入 |

---

## 🛡 安全

- ✅ API 密钥全部通过环境变量注入，不写入代码
- ✅ XSS 防护：DOMPurify 覆盖全部 v-html 渲染
- ✅ 硬编码密钥已清理（百度千帆 AccessTokenService 已删除）
- ✅ .gitignore 排除敏感配置文件

---

## 📊 题库规模

| 专业方向 | 练习套数 | 题量 |
|----------|---------|------|
| 人工智能基础 | 6 | ~60 |
| Python 编程 | 6 | ~55 |
| 机器学习/深度学习 | 6 | ~55 |
| 计算机基础 | 6 | ~55 |
| 机械工程基础 | 6 | ~55 |
| **总计** | **30** | **~270** |

---

**穹镜 Cosmora** — 从学习画像到资源生成的 AI 全链路学习平台。
