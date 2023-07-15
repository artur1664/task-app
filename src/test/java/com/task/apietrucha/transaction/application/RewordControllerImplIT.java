package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.rest.RestConstants.APP_BASE_URL_V1;
import static com.task.apietrucha.transaction.application.contract.RewordController.REWORD_URL;
import static com.task.apietrucha.transaction.domain.adapter.RewordsService.GREAT_CUSTOMER_MESSAGE;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.task.apietrucha.ApplicationTestBase;
import com.task.apietrucha.factory.PointsTestDataFactory;
import com.task.apietrucha.factory.PurchaseTestDataFactory;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

class RewordControllerImplIT extends ApplicationTestBase {

    @Test
    void get_reword_info_should_return_ok() throws Exception {
        //given
        var customerId = 1L;
        var purchase = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId).build());
        //and
        pointsRepository.save(PointsTestDataFactory
            .prepare(purchase, customerId)
            .createdYear(2022)
            .createdMonth(1)
            .points(1000)
            .build());
        //when
        ResultActions result = mockMvc.perform(get(APP_BASE_URL_V1 + REWORD_URL, customerId));
        //then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.reword", is(GREAT_CUSTOMER_MESSAGE)));
    }
}