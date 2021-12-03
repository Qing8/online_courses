package com.example.online_courses;

import com.example.online_courses.entity.User;
import com.example.online_courses.mapper.UserMapper;
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

}
