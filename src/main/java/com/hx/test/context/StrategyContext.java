package com.hx.test.context;

import com.hx.test.server.Strategy;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class StrategyContext {
    private Strategy strategy;

    public Integer executeStrategy(Integer a, Integer b) {
        return strategy.toHandle(a, b);
    }
}
