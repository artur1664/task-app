package com.task.apietrucha.transaction.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.task.apietrucha.DbTestBase;
import com.task.apietrucha.factory.PointsTestDataFactory;
import com.task.apietrucha.factory.PurchaseTestDataFactory;
import com.task.apietrucha.shared.DateTimeUtils;
import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.rest.PointsResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class PointsServiceImplIT extends DbTestBase {

    private PointsService service;

    @MockBean
    private DateTimeUtils dateTimeUtils;

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

    @Test
    void get_points_from_last_three_months_should_return_results_including_previous_year_starting_from_january() {
        //given
        var customerId = 1L;
        var purchase = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase1 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase2 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase3 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase4 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase5 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase6 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase7 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        //and
        when(dateTimeUtils.getActualYearNumber()).thenReturn(2023);
        when(dateTimeUtils.getActualMonthNumber()).thenReturn(1); //january
        //and
        pointsRepository.save(PointsTestDataFactory.prepare(purchase, customerId).createdYear(2022).createdMonth(1).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase1, customerId).createdYear(2022).createdMonth(2).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase2, customerId).createdYear(2022).createdMonth(12).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase3, customerId).createdYear(2022).createdMonth(12).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase4, customerId).createdYear(2022).createdMonth(11).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase5, customerId).createdYear(2023).createdMonth(1).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase6, customerId).createdYear(2023).createdMonth(1).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase7, customerId).createdYear(2023).createdMonth(1).points(1).build());
        //when
        PointsResponse response = service.getPointsFromLastThreeMonths(customerId);
        //then
        int expectedPerMonthResultsCount = 3;
        assertEquals(expectedPerMonthResultsCount, response.pointsPerMonth().size());
        assertEquals(8, response.total());
        assertEquals("2022-11", response.pointsPerMonth().get(0).month());
        assertEquals(1, response.pointsPerMonth().get(0).points());
        assertEquals("2022-12", response.pointsPerMonth().get(1).month());
        assertEquals(2, response.pointsPerMonth().get(1).points());
        assertEquals("2023-1", response.pointsPerMonth().get(2).month());
        assertEquals(3, response.pointsPerMonth().get(2).points());
    }

    @Test
    void get_points_from_last_three_months_should_return_results_including_previous_year_starting_from_february() {
        //given
        var customerId = 1L;
        var purchase = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase1 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase2 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase3 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase4 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase5 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase6 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase7 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        //and
        when(dateTimeUtils.getActualYearNumber()).thenReturn(2023);
        when(dateTimeUtils.getActualMonthNumber()).thenReturn(2); //february
        //and
        pointsRepository.save(PointsTestDataFactory.prepare(purchase, customerId).createdYear(2022).createdMonth(1).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase1, customerId).createdYear(2022).createdMonth(2).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase2, customerId).createdYear(2022).createdMonth(12).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase3, customerId).createdYear(2022).createdMonth(12).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase4, customerId).createdYear(2022).createdMonth(11).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase5, customerId).createdYear(2023).createdMonth(1).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase6, customerId).createdYear(2023).createdMonth(2).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase7, customerId).createdYear(2023).createdMonth(1).points(1).build());
        //when
        PointsResponse response = service.getPointsFromLastThreeMonths(customerId);
        //then
        int expectedPerMonthResultsCount = 3;
        assertEquals(expectedPerMonthResultsCount, response.pointsPerMonth().size());
        assertEquals(8, response.total());
        assertEquals("2022-12", response.pointsPerMonth().get(0).month());
        assertEquals(2, response.pointsPerMonth().get(0).points());
        assertEquals("2023-1", response.pointsPerMonth().get(1).month());
        assertEquals(2, response.pointsPerMonth().get(1).points());
        assertEquals("2023-2", response.pointsPerMonth().get(2).month());
        assertEquals(1, response.pointsPerMonth().get(2).points());
    }
}