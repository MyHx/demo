package com.hx.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 测试异步处理
 *
 * @author hexian
 */
@Slf4j
@Service
public class TestAsyncService {

    @Async
    public void service1() {
        System.out.println("--------start-service1------------");
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        System.out.println("--------end-service1------------");
    }
}