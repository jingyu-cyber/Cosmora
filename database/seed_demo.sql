-- Demo data for Qiongjing Cosmora
INSERT INTO user (user_id, username, password, role, phone) VALUES ('15023569607','Yu','$2a$10$demo','student','15023569607');
INSERT INTO school (name, location) VALUES ('Demo University','Shanghai');
INSERT INTO course (course_name, introduction, type) VALUES ('AI Basics','Introduction to AI','core');
INSERT INTO course (course_name, introduction, type) VALUES ('Python Programming','Python basics','core');
INSERT INTO class (class_name, course_id, t_id, semester, weeks, number, progress) VALUES ('AI-2026',1,'15023569607','2026-Spring',16,30,'Not started');