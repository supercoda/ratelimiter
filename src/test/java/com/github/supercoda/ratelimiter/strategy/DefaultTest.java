package com.github.supercoda.ratelimiter.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultTest {
    @Test
    public void testIsWithinLimitShouldReturnTrueRegardless() {
        Default rateLimiter = new Default();

        assertTrue(rateLimiter.isWithinLimit());
    }

    @Test
    public void testGetWaitTimeShouldReturnZeroRegardless() {
        Default rateLimiter = new Default();

        assertEquals(0, rateLimiter.getWaitTime());
    }
}
