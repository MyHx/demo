package com.hx.strategy.controller;

import com.hx.strategy.service.StrategyMultiply;
import com.hx.strategy.context.StrategyContext;

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
