package com.hx.test.controller;

import com.hx.test.dao.UserJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hexian
 * @Date: 2021/04/22 18:19
 */
@RestController
public class HelloController {

    @Value("${my.name}")
    String name;

    @Autowired
    private UserJpaDao userJpaDao;

    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/list")
    public void list() {
        long count = userJpaDao.count();
        System.out.println(count);
    }

}
