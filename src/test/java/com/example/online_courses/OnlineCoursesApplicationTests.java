package com.example.online_courses;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.online_courses.entity.User;
import com.example.online_courses.mapper.UserMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        user.setName("noAge");
//        user.setAge(75);
        user.setEmail("noAge@youtube.com");
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

    @Test
    void testSelectUser(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2));
        for (User u : users){
            System.out.println(u.getName());
        }
    }

    @Test
    void testSelectByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        map.put("age", 18);
        List<User> user = userMapper.selectByMap(map);
        System.out.println(user);
    }

    @Test
    void testSelectPage(){
        Page<User> page = new Page<>(1, 2);
        userMapper.selectPage(page, null);

        System.out.println(page.getCurrent());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
    }

    @Test
    void testPhysicalDeletion(){
        // logical deletion of entry with user id = 1L
        int result = userMapper.deleteById(1L);
    }

    @Test
    void testLogicalDeletion(){
        int resut = userMapper.deleteById(1L);
    }

    @Test
    void testComplexQuery(){
        // 创建对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 20);
        wrapper.like("name","co");
        wrapper.select("id", "name");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testDelete(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNull("name")
                .ge("age", 12)
                .isNotNull("email");
        int res = userMapper.delete(queryWrapper);
        System.out.println("delete return count = " + res);
    }

    @Test
    public void testSelectionEq(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Tom");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectionBetween(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 30);
        int count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    @Test
    public void testSelectionAllEq(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name","Jack");
        map.put("age", 20);
        queryWrapper.allEq(map);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectionLike(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
//                .notLike("name", "e")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> ele : maps){
            System.out.println(ele.get("age"));
        }
    }

    @Test
    void testSelectionInNotIn() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.in("id", 1, 3, 5, 9);
//        wrapper.notIn()
        List<User> userList = userMapper.selectList(wrapper);
        for (User u : userList) {
            System.out.println(u);
        }
    }

    @Test
    public void testSelectioninSqlNotInSql(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("name","select name from user where id < 3");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelection(){
        User user = new User();
        user.setAge(19);
        user.setName("Oliver");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .like("name","h")
                .or()
                .between("age",20,30);
        int result = userMapper.update(user, updateWrapper);
        System.out.println("affected number of rows: " + result);
    }


}
