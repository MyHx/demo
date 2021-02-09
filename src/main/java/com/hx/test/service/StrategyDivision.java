package com.hx.test.service;

import com.hx.test.server.Strategy;

public class StrategyDivision implements Strategy {
    @Override
    public Integer toHandle(Integer param1, Integer param2) {
        return param1 / param2;
    }
}
