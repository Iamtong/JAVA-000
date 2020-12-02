package com.tong.mall.controller;

import com.tong.mall.entity.User;
import com.tong.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id:\\d*}",method = RequestMethod.GET)
    public User Index(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    /**
     * 获取用户列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/user/list/{page:\\d+}",method = RequestMethod.GET)
    public List<User> UsersList(@PathVariable("page") Integer page){
        page = page>0?page:1;
        Integer limit = 3;
        Integer start = (page-1)*limit;
        return userService.getUserList(start,limit);
    }

    /**
     * 添加用户数据
     * @param
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public int Add(@RequestBody User user){
        userService.add(user);
        return user.getId();
    }
}
