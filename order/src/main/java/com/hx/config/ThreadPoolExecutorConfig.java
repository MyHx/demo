//package com.hx.config;
//
//import com.google.common.util.concurrent.ThreadFactoryBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.*;
//
///**
// * @author hexian
// */
//@Slf4j
//@Configuration
//public class ThreadPoolExecutorConfig {
//
//    @Bean
//    public ExecutorService createExecutorService() {
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("hx-pool-%d").build();
//        return new ThreadPoolExecutor(30, 60, 60L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(1024), namedThreadFactory);
//    }
//}
