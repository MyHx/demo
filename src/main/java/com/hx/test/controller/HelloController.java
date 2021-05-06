package com.hx.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hexian
 * @Date: 2021/04/22 18:19
 */
@RestController
public class HelloController {

    @Value("${my.name}")
    String name;

    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello %s!", name);
    }
}
