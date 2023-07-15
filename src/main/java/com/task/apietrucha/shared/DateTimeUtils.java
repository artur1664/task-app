package com.task.apietrucha.shared;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DateTimeUtils {

    private final Clock clock;

    public int getActualMonthNumber() {
        return LocalDateTime.now(clock).getMonthValue();
    }

    public int getActualYearNumber() {
        return LocalDateTime.now(clock).getYear();
    }
}
