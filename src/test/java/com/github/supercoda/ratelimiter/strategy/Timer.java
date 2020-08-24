package com.github.supercoda.ratelimiter.strategy;

public class Timer implements ITime {
    private long currentTime;

    public Timer(long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public long getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(long newTime) {
        this.currentTime = newTime;
    }
}
