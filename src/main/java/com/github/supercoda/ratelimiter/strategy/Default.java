package com.github.supercoda.ratelimiter.strategy;

public class Default implements IStrategy {

    @Override
    public boolean isWithinLimit() {
        return true;
    }

    @Override
    public long getWaitTime() {
        return 0;
    }
}
