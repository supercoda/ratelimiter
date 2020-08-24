package com.github.supercoda.ratelimiter.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RateLimiterTest {

    @Test
    public void testIsWithinLimitShouldReturnFalseWhenLimitIsZero() {
        Timer timer = new Timer(100);
        RateLimiter rateLimiter = new RateLimiter(0, 10, timer);

        assertFalse(rateLimiter.isWithinLimit());
        assertEquals(10, rateLimiter.getWaitTime());
    }

    @Test
    public void testIsWithinLimitShouldReturnTrueWhenUserMakingInitialRequest() {
        Timer timer = new Timer(100);
        RateLimiter rateLimiter = new RateLimiter(1, 10, timer);

        assertEquals(0, rateLimiter.getWaitTime());
        assertTrue(rateLimiter.isWithinLimit());
        assertEquals(10, rateLimiter.getWaitTime());
    }

    @Test
    public void testRateLimiter() {
        Timer timer = new Timer(90);
        RateLimiter rateLimiter = new RateLimiter(1, 10, timer);

        assertEquals(0, rateLimiter.getWaitTime());
        assertTrue(rateLimiter.isWithinLimit());
        assertEquals(10, rateLimiter.getWaitTime());
        assertFalse(rateLimiter.isWithinLimit());

        timer.setCurrentTime(95);
        assertEquals(5, rateLimiter.getWaitTime());

        timer.setCurrentTime(99);
        assertEquals(1, rateLimiter.getWaitTime());
    }
}
