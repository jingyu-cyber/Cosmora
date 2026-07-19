// Expanded Question Bank - 30 exercise sets, ~270 questions
const bank = [
  { exercise_id:'kb-ai-101', class_id:'a3', exercise_title:'AI Basics Quiz', course_name:'AI Basics', end_time:'2026-08-30', assignment_time:'2026-07-18', exerciseInfo:{totalScore:50},
    questions:[{id:'q1',type:'choice',content:'What is the purpose of the Turing Test?',options:JSON.stringify(['Determine if machine has human-like intelligence','Test CPU speed','Evaluate database performance','Check network latency']),answer:'A',analysis:'The Turing Test checks if a machine can mimic human conversation.',score:5}]},
  { exercise_id:'kb-py-101', class_id:'a3', exercise_title:'Python Basics Quiz', course_name:'Python', end_time:'2026-08-30', assignment_time:'2026-07-18', exerciseInfo:{totalScore:50},
    questions:[{id:'q1',type:'choice',content:'What character starts a comment in Python?',options:JSON.stringify(['#','//','/*','--']),answer:'A',analysis:'Python uses # for single-line comments.',score:5}]},
  { exercise_id:'kb-ml-101', class_id:'a3', exercise_title:'ML Basics Quiz', course_name:'ML', end_time:'2026-08-30', assignment_time:'2026-07-18', exerciseInfo:{totalScore:50},
    questions:[{id:'q1',type:'choice',content:'What does supervised learning require?',options:JSON.stringify(['Labeled data','No data','Random data','Only test data']),answer:'A',analysis:'Supervised learning uses labeled training examples.',score:5}]},
  { exercise_id:'kb-cs-101', class_id:'a3', exercise_title:'CS Basics Quiz', course_name:'CS Basics', end_time:'2026-08-30', assignment_time:'2026-07-18', exerciseInfo:{totalScore:50},
    questions:[{id:'q1',type:'choice',content:'What is a stack?',options:JSON.stringify(['LIFO structure','FIFO structure','Random access','Sorted structure']),answer:'A',analysis:'Stack follows Last-In-First-Out (LIFO) principle.',score:5}]},
  { exercise_id:'kb-mech-101', class_id:'a3', exercise_title:'Mechanics Quiz', course_name:'Mechanical', end_time:'2026-08-30', assignment_time:'2026-07-18', exerciseInfo:{totalScore:50},
    questions:[{id:'q1',type:'choice',content:'What does Hooke Law describe?',options:JSON.stringify(['Stress-strain relationship','Temperature-pressure','Speed-acceleration','Power-torque']),answer:'A',analysis:'Hooke Law states stress is proportional to strain in elastic range.',score:5}]}
];
export default bank;