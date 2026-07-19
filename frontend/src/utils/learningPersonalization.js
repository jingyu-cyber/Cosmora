export function getCurrentUser(){ try{ return JSON.parse(localStorage.getItem('user'))||{}; }catch(e){ return {}; } }
export function isDemoUser(user){ return user&&(user.userId==='15023569607'||user.role==='demo'); }
export function getPrimaryLearningDirection(user){ if(isDemoUser(user)) return 'AI Basics'; return user?.targetDirection||'AI Basics'; }
export function getMatchedKnowledgeIds(user){ return ['ai-basic','machine-learning']; }