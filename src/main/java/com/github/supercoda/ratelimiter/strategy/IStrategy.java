package com.github.supercoda.ratelimiter.strategy;

public interface IStrategy {
    public boolean isWithinLimit();
    public long getWaitTime();
}
