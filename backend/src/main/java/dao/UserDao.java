package com.example.lessondesign.dao;
import com.example.lessondesign.bean.User;
import org.apache.ibatis.annotations.*;
@Mapper
public interface UserDao {
    @Select("SELECT * FROM user WHERE user_id = #{userId}") User findByUserId(@Param("userId") String userId);
    @Insert("INSERT INTO user (user_id, username, password, role, phone, title, email, school_id, created_at) VALUES (#{userId}, #{username}, #{password}, #{role}, #{phone}, #{title}, #{email}, #{schoolId}, #{createdAt})") int SignUp(User user);
    @Update("UPDATE user SET username=#{username}, gender=#{gender}, title=#{title}, email=#{email}, phone=#{phone}, research=#{research}, target_direction=#{targetDirection} WHERE user_id=#{userId}") boolean updateUser(User user);
    @Select("SELECT role FROM user WHERE user_id = #{userId}") String findRoleByUserId(@Param("userId") String userId);
    @Select("SELECT COUNT(*) > 0 FROM user WHERE user_id = #{id} AND role = #{role}") boolean existsByIdAndRole(@Param("id") String id, @Param("role") String role);
}