package com.hx.scene.bean;

import javax.annotation.PreDestroy;

/**
 * @author hex
 */
public class Destroy {

    @PreDestroy
    public void destroy() {
        System.out.println("使用@PreDestroy注解Bean销毁执行了destroy()方法");
    }
}