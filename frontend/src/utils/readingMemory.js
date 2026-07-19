const MEMORY_KEY='cosmora_reading_memory';const QUIZ_KEY='cosmora_reader_quiz_assignments';
export function getReadingMemory(user){ try{ const k=MEMORY_KEY+'_'+(user?.userId||'demo'); return JSON.parse(localStorage.getItem(k)||'{}'); }catch(e){return{}} }
export function getReadingSnapshotForUser(user){ const m=getReadingMemory(user); return {books:Object.keys(m).length,highlights:0,notes:0}; }
export function updateReadingProgress(user,bookId,chapterId,page,total){ try{const k=MEMORY_KEY+'_'+(user?.userId||'demo');const m=JSON.parse(localStorage.getItem(k)||'{}');if(!m[bookId])m[bookId]={};m[bookId][chapterId]={page,total,updated:Date.now()};localStorage.setItem(k,JSON.stringify(m))}catch(e){}}
export function upsertReadingHighlight(user,bookId,chapterId,content){}
export function upsertReadingNote(user,bookId,chapterId,content){}
export function recordUploadedDocInsight(user,fileId,summary){}
export function saveReaderQuizAssignment(user,assignments){ try{const k=QUIZ_KEY+'_'+(user?.userId||'demo');localStorage.setItem(k,JSON.stringify(assignments))}catch(e){} }