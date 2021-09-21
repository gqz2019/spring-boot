package com.gqz.springbootprovider.controller;

import com.gqz.springbootprovider.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-20 21:03
 **/
@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("{id}")
    public User findById(@PathVariable("id") Integer id){

        System.out.println("我从数据库获取数据了");

        User user = new User();

        user.setId(id);
        user.setName("gqz");
        return user;

    }
}
