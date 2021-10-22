package com.gqz.springbootconsumer.feign;

import com.gqz.springbootconsumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>feign的请求服务器</p>
 *
 * @author gqz20
 * @create 2021-09-22 16:01
 **/
@FeignClient("provider")
public interface BasicClient {
    @GetMapping("user/{id}")
    User findById(@PathVariable("id") Integer id);
}
