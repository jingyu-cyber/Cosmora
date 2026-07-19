package com.example.lessondesign.dao;
import com.example.lessondesign.bean.School;
import org.apache.ibatis.annotations.*;
@Mapper
public interface SchoolDao {
    @Select("SELECT * FROM school WHERE name = #{name}") School findByName(@Param("name") String name);
    @Select("SELECT * FROM school WHERE id = #{id}") School findById(@Param("id") Integer id);
    @Insert("INSERT INTO school (name, location) VALUES (#{name}, #{location})") @Options(useGeneratedKeys=true, keyProperty="id") int insertSchool(School s);
}