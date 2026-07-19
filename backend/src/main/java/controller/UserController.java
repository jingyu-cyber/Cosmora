package com.example.lessondesign.controller;
import com.example.lessondesign.bean.*;
import com.example.lessondesign.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired private UserDao userDao;
    @Autowired private SchoolDao schoolDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @PostMapping public ResponseEntity<Map<String,Object>> registerUser(@RequestBody Map<String,String> p) {
        Map<String,Object> r=new HashMap<>();
        try {
            String phone=p.get("phone"),password=p.get("password"),schoolName=p.get("schoolName"),username=p.get("username");
            if(phone==null||password==null||schoolName==null){r.put("success",false);r.put("message","Required fields missing");return ResponseEntity.badRequest().body(r);}
            User exists=userDao.findByUserId(phone);
            if(exists!=null){r.put("success",false);r.put("message","Phone already registered");return ResponseEntity.status(HttpStatus.CONFLICT).body(r);}
            School school=schoolDao.findByName(schoolName);
            if(school==null){School ns=new School();ns.setName(schoolName);schoolDao.insertSchool(ns);school=schoolDao.findByName(schoolName);}
            User user=new User();user.setUserId(phone);user.setUsername(username!=null?username:"Student");user.setPhone(phone);user.setPassword(passwordEncoder.encode(password));user.setRole("student");user.setSchoolId(school.getId());
            if(userDao.SignUp(user)>0){user.setPassword(null);r.put("success",true);r.put("message","OK");r.put("userId",phone);return ResponseEntity.ok(r);}
            r.put("success",false);r.put("message","Failed");return ResponseEntity.status(500).body(r);
        }catch(Exception e){r.put("success",false);r.put("message",e.getMessage());return ResponseEntity.status(500).body(r);}
    }
    @PostMapping("/login") public ResponseEntity<Map<String,Object>> login(@RequestBody Map<String,String> d) {
        Map<String,Object> r=new HashMap<>();
        User user=userDao.findByUserId(d.get("phone"));
        if(user==null||!passwordEncoder.matches(d.get("password"),user.getPassword())){r.put("success",false);r.put("message","Invalid credentials");return ResponseEntity.status(401).body(r);}
        user.setPassword(null);r.put("success",true);r.put("user",user);return ResponseEntity.ok(r);
    }
    @PostMapping("/update") public ResponseEntity<Map<String,Object>> updateUser(@RequestBody User user) {
        Map<String,Object> r=new HashMap<>();
        boolean ok=userDao.updateUser(user);r.put("success",ok);return ok?ResponseEntity.ok(r):ResponseEntity.status(400).body(r);
    }
    @GetMapping("/profile/{userId}") public ResponseEntity<Map<String,Object>> getUserProfile(@PathVariable String userId) {
        Map<String,Object> r=new HashMap<>();
        User u=userDao.findByUserId(userId);if(u==null){r.put("success",false);return ResponseEntity.status(404).body(r);}
        r.put("success",true);r.put("data",u);return ResponseEntity.ok(r);
    }
    @GetMapping("/role") public ResponseEntity<Map<String,Object>> getUserRole(@RequestParam String userId) {
        Map<String,Object> r=new HashMap<>();r.put("role",userDao.findRoleByUserId(userId));return ResponseEntity.ok(r);
    }
}