package com.task.apietrucha.factory;

import com.task.apietrucha.transaction.domain.entity.Purchase;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseRequest;
import java.math.BigDecimal;

public class PurchaseTestDataFactory {

    public static Purchase.PurchaseBuilder prepare(BigDecimal amount, Long customerId) {
        return Purchase.builder()
            .customerId(customerId)
            .amount(amount);
    }

    public static CreatePurchaseRequest.CreatePurchaseRequestBuilder prepareCreateRequest(BigDecimal amount, Long customerId) {
        return CreatePurchaseRequest.builder()
            .amount(amount)
            .customerId(customerId);
    }

    public static UpdatePurchaseRequest.UpdatePurchaseRequestBuilder prepareUpdateRequest(BigDecimal newValue) {
        return UpdatePurchaseRequest.builder()
            .newValue(newValue);
    }
}
