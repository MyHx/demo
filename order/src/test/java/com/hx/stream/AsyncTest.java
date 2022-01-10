package com.hx.stream;

import com.hx.async.TestAsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: hexian
 * @Date: 2021/04/27 18:46
 */
@Component
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AsyncTest {

    @Autowired
    public TestAsyncService testAsyncService;

    @Test
    public void test1() throws InterruptedException {
        System.out.println("aaa");
        testAsyncService.service1();
        // 模拟耗时
        System.out.println("bbb");
    }

}
