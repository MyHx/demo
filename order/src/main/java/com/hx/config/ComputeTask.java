package com.hx.config;

import java.util.concurrent.Callable;

/**
 * 计算任务
 *
 * @author hexian
 */
public class ComputeTask implements Callable<Integer> {

    private Integer result;
    private String taskName;

    public ComputeTask(Integer iniResult, String taskName) {
        result = iniResult;
        this.taskName = taskName;
        System.out.println("生成子线程计算任务: " + taskName);
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public Integer call() throws Exception {
        // TODO Auto-generated method stub

        for (int i = 1; i <= 100; i++) {
            result += i;
        }
        // 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成。
        Thread.sleep(5000);
        System.out.println("子线程计算任务: " + getTaskName() + " 执行完成!");
        return result;
    }
}