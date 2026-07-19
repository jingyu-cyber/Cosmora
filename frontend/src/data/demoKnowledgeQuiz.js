import expanded from './expandedQuestionBank';
const demo = [
  { exercise_id:'demo-1', class_id:'a3-demo', exercise_title:'AI Basics Quiz', course_name:'AI Basics', end_time:'2026-07-25', assignment_time:'2026-07-18', exerciseInfo:{totalScore:30},
    questions:[{id:'q1',type:'choice',content:'What does supervised learning require?',options:JSON.stringify(['Labeled data','Unlabeled data','No data','Random data']),answer:'A',analysis:'Supervised learning uses labeled training data.',score:10}] }
];
export default [...demo, ...expanded];