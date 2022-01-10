package com.hx.strategy.service;

import com.hx.strategy.server.Strategy;

/**
 * 策略具体除法实现
 */
public class StrategyDivision implements Strategy {
    @Override
    public Integer toHandle(Integer param1, Integer param2) {
        return param1 / param2;
    }
}
