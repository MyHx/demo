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
        userList.add(new User(1, "pan_junbiao的博客_01", "男", 32, "研发部", BigDecimal.valueOf(1600)));
        userList.add(new User(2, "pan_junbiao的博客_02", "男", 25, "财务部", BigDecimal.valueOf(1800)));
        userList.add(new User(3, "pan_junbiao的博客_03", "女", 20, "人事部", BigDecimal.valueOf(1700)));
        userList.add(new User(4, "pan_junbiao的博客_04", "男", 30, "研发部", BigDecimal.valueOf(1500)));
        userList.add(new User(5, "pan_junbiao的博客_05", "女", 25, "财务部", BigDecimal.valueOf(1200)));

        return userList;
    }
}