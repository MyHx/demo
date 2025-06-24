package com.hx.base.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * @author LION
// * @version 1.0
// * @className ThreadExecutorConfig
// * @description 线程池配置
// * @date
// **/
//@Slf4j
//@Configuration
//@EnableAsync
//public class ThreadExecutorConfig {
//
//    /**
//     * 核心线程数
//     */
//    @Value("${thread.pool.core.size:30}")
//    private Integer corePoolSize;
//    /**
//     * 最大线程数
//     */
//    @Value("${thread.pool.max.size:60}")
//    private Integer maxPoolSize;
//    /**
//     * 队列数
//     */
//    @Value("${thread.pool.queue.size:99999}")
//    private Integer queueCapacity;
//    /**
//     * 是否允许核心线程超时
//     */
//    @Value("${thread.pool.core.thread.timeout:true}")
//    private Boolean allowCoreThreadTimeOut;
//
//    @Bean(name = "executorService")
//    public ExecutorService executor() {
//        log.info("start executor testExecutor ");
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(corePoolSize);
//        executor.setMaxPoolSize(maxPoolSize);
//        executor.setQueueCapacity(queueCapacity);
//        executor.setThreadNamePrefix("executor-service-");
//        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
//
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        // 执行初始化
//        executor.initialize();
//        return executor.getThreadPoolExecutor();
//    }
//
//}
