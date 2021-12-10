package com.example.online_courses;

import com.example.online_courses.entity.User;
import com.example.online_courses.mapper.UserMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OnlineCoursesApplicationTests {

    @Autowired
    private UserMapper userMapper;

    // display user table entries
    @Test
    void contextLoads() {
        List<User> userList =  userMapper.selectList(null);
        System.out.println(userList);
    }

    @Test
    void addUser(){
        User user = new User();
        user.setName("Lucy");
        user.setAge(53);
        user.setEmail("lucy@youtube.com");
        int insert = userMapper.insert(user);
        System.out.println("insert result:" + insert);
    }

    @Test
    void updateUser(){
        User user = new User();
        user.setId(2L);
        String name = user.getName();
        System.out.println(user);
    }


}
