// Textbook Library - 5 majors, 9 books each
const library = [
  { directionId:'ai-basic', directionName:'AI Basics', books:[{id:'ai-1',title:'AI Introduction',author:'SmartEdu',source:'SmartEdu',access:'Public',url:'https://higher.smartedu.cn/',level:'Beginner',chapters:[{id:'c1',title:'AI Concepts',pages:24,keypoints:['Agent','Search']}]}]},
  { directionId:'python', directionName:'Python', books:[{id:'py-1',title:'Python 3 Tutorial',author:'Runoob',source:'Runoob',access:'Public',url:'https://www.runoob.com/python3/',level:'Beginner',chapters:[{id:'c1',title:'Syntax',pages:26,keypoints:['Variables','Types']}]}]},
  { directionId:'machine-learning', directionName:'ML', books:[{id:'ml-1',title:'ML Basics',author:'Datawhale',source:'Datawhale',access:'Public',url:'https://www.datawhale.cn/',level:'Beginner',chapters:[{id:'c1',title:'ML Concepts',pages:28,keypoints:['Samples','Features']}]}]},
  { directionId:'computer-basic', directionName:'CS Basics', books:[{id:'cs-1',title:'CS Fundamentals',author:'MOOC',source:'MOOC',access:'Public',url:'https://www.icourse163.org/',level:'Beginner',chapters:[{id:'c1',title:'Data Structures',pages:30,keypoints:['Stack','Queue']}]}]},
  { directionId:'mechanical-basic', directionName:'Mechanical', books:[{id:'mech-1',title:'Mechanics',author:'XuetangX',source:'XuetangX',access:'Public',url:'https://www.xuetangx.com/',level:'Beginner',chapters:[{id:'c1',title:'Statics',pages:28,keypoints:['Force','Moment']}]}]}
];
export default library;