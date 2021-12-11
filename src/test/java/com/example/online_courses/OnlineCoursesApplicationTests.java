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
        user.setName("Colly");
        user.setAge(75);
        user.setEmail("Colly@youtube.com");
        int insert = userMapper.insert(user);
        System.out.println("insert result:" + insert);
    }

    @Test
    void updateUser(){
        User user = new User();
        user.setId(1469339262032805889L); // the id of Colly
        user.setAge(10);
        String name = user.getName();
        int row_num = userMapper.updateById(user);
        System.out.println(row_num);
    }


}
