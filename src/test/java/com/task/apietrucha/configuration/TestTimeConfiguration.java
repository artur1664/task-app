package com.task.apietrucha.configuration;

import static java.time.ZoneOffset.UTC;

import java.time.Clock;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Slf4j
@Profile("it")
@Configuration
public class TestTimeConfiguration {

    public static final ZonedDateTime CURRENT_TIME = ZonedDateTime.of(2023, 6, 1, 10, 0, 0, 0, UTC);

    @Primary
    @Bean
    public Clock getClockProvider() {
        log.warn("************************** configuring clock for tests **************************");
        return Clock.fixed(CURRENT_TIME.toInstant(), CURRENT_TIME.getZone());
    }
}
