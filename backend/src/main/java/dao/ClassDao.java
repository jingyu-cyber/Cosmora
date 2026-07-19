package com.example.lessondesign.dao;
import com.example.lessondesign.bean.Classes;
import org.apache.ibatis.annotations.*;
import java.util.*;
@Mapper
public interface ClassDao {
    @Select("SELECT * FROM class") List<Classes> findAll();
    @Select("SELECT * FROM class WHERE id = #{id}") Classes findById(@Param("id") Integer id);
    @Select("SELECT * FROM class WHERE course_id = #{courseId}") List<Classes> findByCourseId(@Param("courseId") Integer courseId);
    @Select("SELECT * FROM class WHERE t_id = #{tId}") List<Classes> findByTeacherId(@Param("tId") String tId);
    @Insert("INSERT INTO class (class_name, course_id, t_id, semester, weeks, location, start_time, finish_time, number, progress) VALUES (#{className}, #{courseId}, #{tId}, #{semester}, #{weeks}, #{location}, #{startTime}, #{finishTime}, #{number}, #{progress})") @Options(useGeneratedKeys=true, keyProperty="id") int insert(Classes c);
    @Update("UPDATE class SET class_name=#{className}, number=#{number}, progress=#{progress} WHERE id=#{id}") int update(Classes c);
    @Delete("DELETE FROM class WHERE id=#{id}") int deleteById(@Param("id") Integer id);
}