package com.task.apietrucha.shared.configuration;

import java.time.Clock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Instead of using in LocalDateTime.now() etc. use clock bean This can be overriden in tests so all tests will be
 * independent of actual time
 */

@Configuration
public class TimeConfiguration {

    @Bean
    @ConditionalOnMissingBean(Clock.class)
    public Clock getClock() {
        return Clock.systemUTC();
    }
}
