package com.task.apietrucha.transaction.domain;

import com.task.apietrucha.shared.DateTimeUtils;
import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.projections.PointsProjection;
import com.task.apietrucha.transaction.domain.rest.PointsResponse;
import com.task.apietrucha.transaction.infrastructure.PointsRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
class PointsServiceImpl implements PointsService {

    private final PointsRepository pointsRepository;
    private final DateTimeUtils dateTimeUtils;

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

    @Override
    public PointsResponse getPointsFromLastThreeMonths(Long customerId) {
        log.info("Get points from last 3 months for customer id: {}", customerId);
        int actualYear = dateTimeUtils.getActualYearNumber();
        int actualMonth = dateTimeUtils.getActualMonthNumber();
        int minusMonths = 3;
        List<PointsProjection> pointsProjection = pointsRepository
            .findPointsFromLastThreeMonthsForCustomerId(customerId, actualYear, actualMonth - minusMonths);
        Long totalCustomerPoints = pointsRepository.findTotalPointsForCustomerId(customerId);
        PointsResponse response = PointsResponse.from(pointsProjection, totalCustomerPoints);
        log.info("Returning customer points from last 3 months. {}", response);
        return response;
    }

    @Override
    public Long getTotalPointsForCustomer(Long customerId) {
        log.info("Get total points for customer: {}", customerId);
        return pointsRepository.findTotalPointsForCustomerId(customerId);
    }
}
