package com.hx.jdk_aop.service;

import com.hx.jdk_aop.server.UserServer;

/**
 * 用户实现类
 *
 * @author hexian
 * @date 2021/4/6 22:14
 */
public class UserServiceImpl implements UserServer {
    @Override
    public void add() {
        System.out.println("添加了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }

    @Override
    public void update() {
        System.out.println("修改了一个用户");
    }

    @Override
    public void find() {
        System.out.println("查找一个用户");
    }
}
