package com.hx.strategy.context;

import com.hx.strategy.server.Strategy;
import lombok.AllArgsConstructor;

/**
 * 策略上下文
 * @author hex
 */
@AllArgsConstructor
public class StrategyContext {
    private Strategy strategy;

    public Integer executeStrategy(Integer a, Integer b) {
        return strategy.toHandle(a, b);
    }
}
