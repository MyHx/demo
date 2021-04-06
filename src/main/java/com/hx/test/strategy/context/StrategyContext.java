package com.hx.test.strategy.context;

import com.hx.test.strategy.server.Strategy;
import lombok.AllArgsConstructor;

/**
 * 策略上下文
 */
@AllArgsConstructor
public class StrategyContext {
    private Strategy strategy;

    public Integer executeStrategy(Integer a, Integer b) {
        return strategy.toHandle(a, b);
    }
}
