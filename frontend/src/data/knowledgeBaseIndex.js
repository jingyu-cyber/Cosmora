const KB_BASE = '/knowledge-base';
const CATEGORIES = ['ai-basic','machine-learning','deep-learning','python','computer-basic','mechanical-basic'];
let cachedIndex = null;
async function loadAllChunks() { if(cachedIndex) return cachedIndex; const all=[]; for(const cat of CATEGORIES){ try{ const resp=await fetch(KB_BASE+'/'+cat+'/chunks.json'); if(resp.ok){ const chunks=await resp.json(); all.push(...chunks.map(c=>({...c,category:cat}))); } }catch(e){} } cachedIndex=all; return all; }
export function buildKnowledgeContext(query, maxResults=5) { return []; }
export default { loadAllChunks, buildKnowledgeContext };