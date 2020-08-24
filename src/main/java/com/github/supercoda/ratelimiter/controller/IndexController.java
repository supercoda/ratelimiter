package com.github.supercoda.ratelimiter.controller;

import com.github.supercoda.ratelimiter.service.RateLimiterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    RateLimiterManager rateLimiterManager;

    @RequestMapping("/")
    public ResponseEntity<?> index() {
        if (!this.rateLimiterManager.canMakeRequest()) {
            long waitTime = this.rateLimiterManager.waitTime();

            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(String.format("Rate limit exceeded. Try again in %d seconds", waitTime));
        }

        return ResponseEntity.ok("Please keep requesting.");
    }
}
