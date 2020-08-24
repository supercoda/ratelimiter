# Rate Limiter

### Overview
This is a simple implementation of a rate limiting module to block requests. 

The following strategies are implemented as part of this module:
* Default - This will always allow the request to pass through
* RateLimiter - This strategy will limit to only 100 requests per hour

Each strategy expose:
* `isWithinLimit()` - this will return `true` if user can make request or `false` if user can not make request  
* `getWaitTime()` -  this will return number of seconds until the user can make another request

### How to use
Implement new strategy or use the existing strategy such as `com.github.supercoda.ratelimiter.strategy.RateLimiter`

Update the `RateLimiterManager` with the strategy that is suitable. 

Then run the following command to start the spring boot server
```
gradle bootRun
```

Access the application through `http://localhost:8080`

### Test
To run the test, run the following command
```
gradle clean test
```

### Technology
* Spring Boot
* Gradle
* Java
