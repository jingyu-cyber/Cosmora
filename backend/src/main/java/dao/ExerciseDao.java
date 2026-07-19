package com.example.lessondesign.dao;
import com.example.lessondesign.bean.*;
import org.apache.ibatis.annotations.*;
import java.util.*;
@Mapper
public interface ExerciseDao {
    @Select("SELECT * FROM exercise") List<Exercise> findAll();
    @Select("SELECT * FROM exercise WHERE id = #{id}") Exercise findById(@Param("id") Integer id);
    @Insert("INSERT INTO exercise (theme, content, title, type, degree, number, total_score, status, creator_id, created_at, updated_at, end_time) VALUES (#{theme}, #{content}, #{title}, #{type}, #{degree}, #{number}, #{totalScore}, #{status}, #{creatorId}, #{createdAt}, #{updatedAt}, #{endTime})") @Options(useGeneratedKeys=true, keyProperty="id") int insert(Exercise e);
    @Update("UPDATE exercise SET theme=#{theme}, content=#{content}, status=#{status}, updated_at=#{updatedAt} WHERE id=#{id}") int update(Exercise e);
    @Delete("DELETE FROM exercise WHERE id=#{id}") int deleteById(@Param("id") Integer id);
    @Select("SELECT e.* FROM exercise e JOIN class_exercise ce ON e.id=ce.exercise_id WHERE ce.class_id=#{classId}") List<Exercise> findByClassId(@Param("classId") Integer classId);
    @Insert("INSERT INTO exercise_question (content, type, options, answer, analysis, score, difficulty, knowledge_point, creator_id, created_at) VALUES (#{content}, #{type}, #{options}, #{answer}, #{analysis}, #{score}, #{difficulty}, #{knowledgePoint}, #{creatorId}, #{createdAt})") @Options(useGeneratedKeys=true, keyProperty="id") int insertQuestion(ExerciseQuestion q);
    @Insert("INSERT INTO exercise_question_rel (exercise_id, question_id, seq) VALUES (#{eid}, #{qid}, #{seq})") void insertQuestionRel(@Param("eid") int eid, @Param("qid") long qid, @Param("seq") int seq);
    @Select("SELECT * FROM exercise_question WHERE id IN (SELECT question_id FROM exercise_question_rel WHERE exercise_id=#{eid})") List<ExerciseQuestion> findQuestionsByExerciseId(@Param("eid") int eid);
}