package com.hx.test.strategy.controller;

import com.hx.test.strategy.context.StrategyContext;
import com.hx.test.strategy.service.StrategyMultiply;

/**
 * 策略模式测试
 */
public class StrategyController {
    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext(new StrategyMultiply());
        Integer integer = strategyContext.executeStrategy(5, 6);
        System.out.println(integer);
    }
}
