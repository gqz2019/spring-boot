package com.gqz.springbootconsumer.controller;

import com.gqz.springbootconsumer.feign.BasicClient;
import com.gqz.springbootconsumer.pojo.User;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-20 21:25
 **/
@RestController
@RequestMapping("movie")
public class MovieController{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private BasicClient basicClient;


    @GetMapping("look")
//    @HystrixCommand(fallbackMethod = "defaultMethod")
    public User look(){

        List<ServiceInstance> instances = discoveryClient.getInstances("provider");

        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance.getUri());

//        User user = restTemplate.getForObject("http://localhost:8080/user/221", User.class);

        User user = basicClient.findById(221);

        return user;
    }
}
