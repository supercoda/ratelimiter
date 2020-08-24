package com.github.supercoda.ratelimiter.strategy;

import java.util.LinkedList;
import java.util.Queue;

public class RateLimiter implements IStrategy {
    private int maxLimit;
    private ITime timer;
    private long waitTimeInSecond;
    private final Queue<Long> queue = new LinkedList<>();

    public RateLimiter() {
        this.maxLimit = 100;
        this.waitTimeInSecond = 60 * 60;
    }

    public RateLimiter(int maxLimit, long waitTimeInSecond, ITime timer) {
        this.maxLimit = maxLimit;
        this.waitTimeInSecond = waitTimeInSecond;
        this.timer = timer;
    }

    @Override
    public boolean isWithinLimit() {
        long currentTimeInSeconds = this.getCurrentTimeInSecond();
        long earliestRequestTimeFromCurrentTime = currentTimeInSeconds - this.waitTimeInSecond;

        this.cleanUpQueue(earliestRequestTimeFromCurrentTime);

        if (this.queue.size() < this.maxLimit) {
            this.queue.add(currentTimeInSeconds);

            return true;
        }

        return false;
    }

    private void cleanUpQueue(long earliestTime) {
        while (!this.queue.isEmpty() && this.queue.peek() <= earliestTime) {
            this.queue.poll();
        }
    }

    @Override
    public long getWaitTime() {
        long currentTimeInSeconds = this.getCurrentTimeInSecond();

        if (this.maxLimit == 0) {
            return this.waitTimeInSecond;
        }

        if (this.queue.size() < this.maxLimit) {
            return 0;
        }

        long earliestRequestTime = this.queue.element();
        long maxWaitTimeSinceEarliestRequestTime = earliestRequestTime + this.waitTimeInSecond;

        return maxWaitTimeSinceEarliestRequestTime - currentTimeInSeconds;
    }

    private long getCurrentTimeInSecond() {
        if (this.timer == null) {
            return System.currentTimeMillis() / 1000L;
        }

        return this.timer.getCurrentTime();
    }
}
