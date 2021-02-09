package com.hx.test.context;

import com.hx.test.server.Strategy;

public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer executeStrategy(Integer a, Integer b) {
        return strategy.toHandle(a, b);
    }
}
