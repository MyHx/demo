package com.hx.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hexian
 * @date 2021/9/18 15:47
 */
@Component
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisServiceTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test1() {
        String s = redisTemplate.opsForValue().get("SNAPSHOT_DETAIL_KEY10878");
        System.out.println(s);
    }
}
