package com.task.apietrucha.factory;

import com.task.apietrucha.transaction.domain.entity.Points;
import com.task.apietrucha.transaction.domain.entity.Purchase;

public class PointsTestDataFactory {

    public static Points.PointsBuilder prepare(Purchase purchase, Long customerId) {
        return Points.builder()
            .purchase(purchase)
            .customerId(customerId);
    }
}
