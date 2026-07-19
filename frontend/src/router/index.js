import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/LoginGlass.vue'
import App from '../views/App.vue'
import FullChain from '../views/FullChain.vue'
import UserProfile from '../views/UserProfile.vue'
import Student from '../views/Student.vue'
import Dashboard from '../views/Dashboard.vue'
import AiInterview from '../views/AiInterview.vue'
import LearningPath from '../views/SmartJobPush.vue'
import MediaResourcesGenerator from '../components/MediaResourcesGenerator.vue'
import ExerciseChange from '../components/ExerciseChange.vue'
Vue.use(VueRouter)
const routes = [
  { path: "/", redirect: "/login" },
  { path: "/login", component: Login },
  { path: '/profile', name: 'Profile', component: UserProfile, meta: { requiresAuth: true } },
  { path: "/app", component: App, meta: { requiresAuth: true }, children: [
    { path: '', redirect: 'full-chain' },
    { path: 'full-chain', name: 'FullChain', component: FullChain },
    { path: 'dashboard', name: 'Dashboard', component: Dashboard },
    { path: 'learning-path', name: 'LearningPath', component: LearningPath },
    { path: 'knowledge-quiz', name: 'KnowledgeQuiz', component: Student },
    { path: 'ai-tutor', name: 'AiTutor', component: AiInterview },
    { path: 'ai-doc', name: 'AiDoc', component: ExerciseChange },
    { path: 'ai-media', name: 'AiMedia', component: MediaResourcesGenerator }
  ]}
]
const router = new VueRouter({ routes })
router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next();
  const userFlag = window.localStorage.getItem("user");
  if (to.matched.some(r => r.meta.requiresAuth) && !userFlag) next('/login');
  else next();
})
export default router