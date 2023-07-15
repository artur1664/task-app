package com.task.apietrucha.transaction.domain;

import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.adapter.RewordsService;
import com.task.apietrucha.transaction.domain.rest.RewordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RewordsServiceImpl implements RewordsService {

    private final PointsService pointsService;

    @Override
    public RewordResponse getRewordInfo(Long customerId) {
        log.info("Checking reword info for customer: {}", customerId);
        Long totalPoints = pointsService.getTotalPointsForCustomer(customerId);
        if (totalPoints == null || totalPoints <= 0) {
            return RewordResponse.from(BAD_CUSTOMER_MESSAGE);
        } else if (totalPoints < 100) {
            return RewordResponse.from(GOOD_CUSTOMER_MESSAGE);
        } else {
            return RewordResponse.from(GREAT_CUSTOMER_MESSAGE);
        }
    }
}
