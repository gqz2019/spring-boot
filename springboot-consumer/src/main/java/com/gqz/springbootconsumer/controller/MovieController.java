package com.gqz.springbootconsumer.controller;

import com.gqz.springbootconsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-20 21:25
 **/
@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("look")
    public User look(){

        User user = restTemplate.getForObject("http://localhost:8080/user/221", User.class);

        return user;
    }
}
