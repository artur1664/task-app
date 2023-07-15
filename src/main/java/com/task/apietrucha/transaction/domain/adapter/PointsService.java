package com.task.apietrucha.transaction.domain.adapter;

import com.task.apietrucha.transaction.domain.rest.PointsResponse;
import java.math.BigDecimal;

public interface PointsService {

    Integer calculate(BigDecimal input);

    PointsResponse getPointsFromLastThreeMonths(Long customerId);

    Long getTotalPointsForCustomer(Long customerId);
}
