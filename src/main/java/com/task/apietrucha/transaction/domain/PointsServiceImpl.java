package com.task.apietrucha.transaction.domain;

import com.task.apietrucha.transaction.domain.adapter.PointsService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class PointsServiceImpl implements PointsService {

    @Override
    public Integer calculate(BigDecimal input) {
        log.info("Calculate points: {}", input);
        int result = 0;

        if (input == null || input.intValue() == 0) {
            return result;
        }

        if (input.compareTo(BigDecimal.valueOf(100)) > 0) {
            result = 2 * (input.intValue() - 100);
        }

        if (input.compareTo(BigDecimal.valueOf(50)) > 0) {
            result += input.intValue() - 50;
        }

        log.info("Calculated points: {}", result);
        return result;
    }
}
