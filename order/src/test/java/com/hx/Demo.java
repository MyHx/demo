package com.hx;


import java.util.concurrent.atomic.AtomicBoolean;


public class Demo {
    public static void main(String[] args) {
        AtomicBoolean connectionClosed = new AtomicBoolean(false);
        while (!connectionClosed.get()) {
            send("内容", connectionClosed);
        }
        if (connectionClosed.get()) {
            System.out.println("连接已关闭");
            return;
        }
        System.out.println("继续逻辑");
    }

    private static void send(String text, AtomicBoolean connectionClosed) {
        try {
            System.out.println(text);
            throw new RuntimeException();
        } catch (Exception e) {
            if (!connectionClosed.getAndSet(true)) {
                System.out.println("客户端已断开连接");
            }
            System.out.println("执行关闭逻辑");
        }
    }
}