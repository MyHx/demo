package com.hx.stream.service;

import com.hx.stream.bean.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息业务逻辑类
 *
 * @author pan_junbiao
 **/
public class UserService {
    /**
     * 获取用户列表
     */
    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>(5);
        userList.add(new User(1, "小红", "男", 32, "研发部", BigDecimal.valueOf(1600)));
        userList.add(new User(2, "小明", "男", 25, "财务部", BigDecimal.valueOf(1800)));
        userList.add(new User(3, "小黄", "女", 20, "人事部", BigDecimal.valueOf(1700)));
        userList.add(new User(4, "小林", "男", 30, "研发部", BigDecimal.valueOf(1500)));
        userList.add(new User(5, "小何", "男", 25, "财务部", BigDecimal.valueOf(1200)));
        userList.add(new User(6, "小何", "男", 25, "财务部", BigDecimal.valueOf(1200)));

        return userList;
    }
}