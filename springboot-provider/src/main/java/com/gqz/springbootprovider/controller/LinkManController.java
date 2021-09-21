package com.gqz.springbootprovider.controller;

import com.gqz.springbootprovider.dao.LinkManDao;
import com.gqz.springbootprovider.pojo.Linkman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-21 14:12
 **/
@RestController
@RequestMapping("linkman")
public class LinkManController {
    @Autowired
    private LinkManDao linkManDao;

    @GetMapping("findAll")
    public List<Linkman> findAll() {
        List<Linkman> all = linkManDao.findAll();
        return all;
    }
}
