package com.hx.jdk_aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 生成代理类
 *
 * @author hexian
 * @date 2021/4/6 22:18
 */
public class ProxyHander implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        logBefore(name);
        Object invoke = method.invoke(target, args);
        logAfter(name);
        return invoke;
    }

    void logBefore(String name) {
        System.out.println("准备要操作了哦,操作的方法名为" + name);
    }

    void logAfter(String name) {
        System.out.println("操作结束了哦，操作的方法名为" + name);
    }
}
