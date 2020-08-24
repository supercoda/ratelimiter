package com.github.supercoda.ratelimiter.service;

import com.github.supercoda.ratelimiter.strategy.IStrategy;
import com.github.supercoda.ratelimiter.strategy.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterManager {
    private IStrategy strategy;

    public RateLimiterManager() {
        this.strategy = new RateLimiter();
    }

    public boolean canMakeRequest() {
        return this.strategy.isWithinLimit();
    }

    public long waitTime() {
        return this.strategy.getWaitTime();
    }
}
