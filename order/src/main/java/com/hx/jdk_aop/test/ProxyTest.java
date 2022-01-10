package com.hx.jdk_aop.test;

import com.hx.jdk_aop.proxy.ProxyHander;
import com.hx.jdk_aop.server.UserServer;
import com.hx.jdk_aop.service.UserServiceImpl;

/**
 * 使用代理测试
 *
 * @author hexian
 * @date 2021/4/6 22:25
 */
public class ProxyTest {
    public static void main(String[] args) {
        //真实的目标类
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色
        ProxyHander proxyHander = new ProxyHander();
        //设置要代理对象
        proxyHander.setTarget(userService);
        //动态生成代理类
        UserServer proxy = (UserServer) proxyHander.getProxy();
        //代理类执行方法
        proxy.delete();
    }
}
