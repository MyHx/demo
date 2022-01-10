package com.hx.strategy.service;

import com.hx.strategy.server.Strategy;

/**
 * 策略具体减法实现
 */
public class StrategySubtraction implements Strategy {
    @Override
    public Integer toHandle(Integer param1, Integer param2) {
        return param1 - param2;
    }
}
