package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.RestConstants.APP_BASE_URL_V1;
import static com.task.apietrucha.transaction.application.contract.PurchaseController.PURCHASE_CREATE_URL;
import static com.task.apietrucha.transaction.application.contract.PurchaseController.PURCHASE_UPDATE_URL;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.task.apietrucha.ApplicationTestBase;
import com.task.apietrucha.factory.PointsTestDataFactory;
import com.task.apietrucha.factory.PurchaseTestDataFactory;
import com.task.apietrucha.transaction.domain.entity.Points;
import com.task.apietrucha.transaction.domain.entity.Purchase;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseResponse;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

class PurchaseControllerImplIT extends ApplicationTestBase {

    @Test
    void create_should_create_new_purchase_and_return_created() throws Exception {
        //given
        var amount = new BigDecimal(120);
        var customerId = 1L;
        var request = PurchaseTestDataFactory.prepareCreateRequest(amount, customerId);
        //when
        ResultActions result = mockMvc.perform(
            post(APP_BASE_URL_V1 + PURCHASE_CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request))
        );
        //then
        result.andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists());
        //and purchase created
        var bodyString = result.andReturn().getResponse().getContentAsString();
        CreatePurchaseResponse response = mapper.readValue(bodyString, CreatePurchaseResponse.class);
        Optional<Purchase> createdPurchase = purchaseRepository.findById(response.id());
        assertTrue(createdPurchase.isPresent());
        assertEquals(1L, createdPurchase.get().getCustomerId());
        assertEquals(0, amount.compareTo(createdPurchase.get().getAmount()));
        //and points created
        Optional<Points> createdPoints = pointsRepository.findByPurchase(createdPurchase.get());
        assertTrue(createdPoints.isPresent());
        assertEquals(110, createdPoints.get().getPoints());
        assertEquals(customerId, createdPurchase.get().getCustomerId());
        assertNotNull(createdPoints.get().getCreatedTs());
        assertNotNull(createdPoints.get().getVersion());
    }

    private static Stream<Arguments> create_invalid_requests() {
        return Stream.of(
            Arguments.of(PurchaseTestDataFactory.prepareCreateRequest(null, 1L), "Amount cannot be null"),
            Arguments.of(PurchaseTestDataFactory.prepareCreateRequest(BigDecimal.ZERO, null),
                "Customer id cannot be null"),
            Arguments.of(PurchaseTestDataFactory.prepareCreateRequest(null, null),
                "Customer id cannot be null, Amount cannot be null")
        );
    }

    @ParameterizedTest
    @MethodSource("create_invalid_requests")
    void create_should_return_bad_request_on_invalid_body(CreatePurchaseRequest request, String expectedErrorMessage)
        throws Exception {
        //when
        ResultActions result = mockMvc.perform(
            post(APP_BASE_URL_V1 + PURCHASE_CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request))
        );
        //then
        result.andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", containsString(expectedErrorMessage)));
    }

    @Test
    void update_should_return_ok() throws Exception {
        //given
        var customerId = 1L;
        var purchase = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.TEN, customerId));
        var points = pointsRepository.save(PointsTestDataFactory.prepare(purchase, customerId).points(0).build());
        //and
        var newValue = BigDecimal.valueOf(100);
        var request = PurchaseTestDataFactory.prepareUpdateRequest(newValue);
        //when
        ResultActions result = mockMvc.perform(put(APP_BASE_URL_V1 + PURCHASE_UPDATE_URL, purchase.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapper.writeValueAsString(request))
        );
        //then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.value", is(request.newValue().intValue())));
        //and purchase updated
        Optional<Purchase> updatedPurchase = purchaseRepository.findById(purchase.getId());
        assertTrue(updatedPurchase.isPresent());
        assertEquals(0, updatedPurchase.get().getAmount().compareTo(newValue));
        //and points recalculated
        Optional<Points> recalculatedPoints = pointsRepository.findByPurchase(updatedPurchase.get());
        assertTrue(recalculatedPoints.isPresent());
        assertEquals(0, points.getPoints()); //old value
        assertEquals(50, recalculatedPoints.get().getPoints()); //new value
    }

    @Test
    void update_should_return_bad_request_on_invalid_body()
        throws Exception {
        //given
        var purchaseId = 1L;
        //and
        var request = PurchaseTestDataFactory.prepareUpdateRequest(null);
        //when
        ResultActions result = mockMvc.perform(
            put(APP_BASE_URL_V1 + PURCHASE_UPDATE_URL, purchaseId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request))
        );
        //then
        result.andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is("New value is required")));
    }

    @Test
    void update_should_return_not_found_on_missing_purchase()
        throws Exception {
        //given
        var purchaseId = 1L;
        //and
        var request = PurchaseTestDataFactory.prepareUpdateRequest(BigDecimal.ZERO);
        //when
        ResultActions result = mockMvc.perform(
            put(APP_BASE_URL_V1 + PURCHASE_UPDATE_URL, purchaseId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request))
        );
        //then
        result.andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message", is("Purchase not found. Id: " + purchaseId)));
    }
}