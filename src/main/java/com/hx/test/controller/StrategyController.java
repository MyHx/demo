package com.hx.test.controller;

import com.hx.test.context.StrategyContext;
import com.hx.test.service.StrategyMultiply;

public class StrategyController {
    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext(new StrategyMultiply());
        Integer integer = strategyContext.executeStrategy(5, 6);
        System.out.println(integer);
    }
}
