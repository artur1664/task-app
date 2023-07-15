package com.task.apietrucha.transaction.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.task.apietrucha.DbTestBase;
import com.task.apietrucha.factory.PointsTestDataFactory;
import com.task.apietrucha.factory.PurchaseTestDataFactory;
import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.rest.PointsResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointsServiceImplIT extends DbTestBase {

    private PointsService service;

    @BeforeEach
    void setUp() {
        service = new PointsServiceImpl(pointsRepository, dateTimeUtils);
    }

    @Test
    void get_points_from_last_three_months_should_not_fail_on_null_argument() {
        //given
        var customerId = 1L;
        var purchase = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        //and
        pointsRepository.save(PointsTestDataFactory.prepare(purchase, customerId).createdYear(2022).createdMonth(1).points(1).build());
        //when
        PointsResponse response = service.getPointsFromLastThreeMonths(null);
        //then
        int expectedPerMonthResultsCount = 0;
        assertEquals(expectedPerMonthResultsCount, response.pointsPerMonth().size());
        assertNull(response.total());
    }
}