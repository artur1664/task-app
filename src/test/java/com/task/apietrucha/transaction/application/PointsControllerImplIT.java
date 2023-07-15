package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.rest.RestConstants.APP_BASE_URL_V1;
import static com.task.apietrucha.transaction.application.contract.PointsController.POINTS_GET_URL;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.task.apietrucha.ApplicationTestBase;
import com.task.apietrucha.factory.PointsTestDataFactory;
import com.task.apietrucha.factory.PurchaseTestDataFactory;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

class PointsControllerImplIT extends ApplicationTestBase {

    @Test
    void get_points_for_customer_should_return_ok() throws Exception {
        //given
        Long customerId = 1L;
        prepareTestData(customerId);
        //when
        ResultActions result = mockMvc.perform(get(APP_BASE_URL_V1 + POINTS_GET_URL, customerId));
        //then
        int expectedTotalCount = 9;
        int expectedPerMonthResultsCount = 3;
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.total", is(expectedTotalCount)))
            .andExpect(jsonPath("$.pointsPerMonth", hasSize(expectedPerMonthResultsCount)))
            //and expect 3 points from month 6
            .andExpect(jsonPath("$.pointsPerMonth[0].points", is(3)))
            .andExpect(jsonPath("$.pointsPerMonth[0].month", is("2023-6")))
            //and expect 2 points from month 5
            .andExpect(jsonPath("$.pointsPerMonth[1].points", is(2)))
            .andExpect(jsonPath("$.pointsPerMonth[1].month", is("2023-5")))
            //and expect 1 points from month 4
            .andExpect(jsonPath("$.pointsPerMonth[2].points", is(1)))
            .andExpect(jsonPath("$.pointsPerMonth[2].month", is("2023-4")));
    }

    @Test
    void get_points_for_customer_should_return_ok_with_no_results() throws Exception {
        //given
        Long customerId = 1L;
        //when
        ResultActions result = mockMvc.perform(get(APP_BASE_URL_V1 + POINTS_GET_URL, customerId));
        //then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.total", nullValue()))
            .andExpect(jsonPath("$.pointsPerMonth", empty()));
    }

    private void prepareTestData(Long customerId) {
        var purchase0 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase1 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase2 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase3 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase4 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase5 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase6 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase7 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        var purchase8 = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());

        //and should not be in results per month
        pointsRepository.save(PointsTestDataFactory.prepare(purchase0, customerId).createdYear(2022).createdMonth(1).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase1, customerId).createdYear(2023).createdMonth(2).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase2, customerId).createdYear(2023).createdMonth(3).points(1).build());
        //and should be in results
        pointsRepository.save(PointsTestDataFactory.prepare(purchase3, customerId).createdYear(2023).createdMonth(4).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase4, customerId).createdYear(2023).createdMonth(6).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase5, customerId).createdYear(2023).createdMonth(6).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase6, customerId).createdYear(2023).createdMonth(5).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase7, customerId).createdYear(2023).createdMonth(5).points(1).build());
        pointsRepository.save(PointsTestDataFactory.prepare(purchase8, customerId).createdYear(2023).createdMonth(6).points(1).build());
    }
}