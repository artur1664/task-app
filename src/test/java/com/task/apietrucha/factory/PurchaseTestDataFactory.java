package com.task.apietrucha.factory;

import com.task.apietrucha.transaction.domain.entity.Purchase;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseRequest;
import java.math.BigDecimal;

public class PurchaseTestDataFactory {

    public static Purchase prepare(BigDecimal amount, Long customerId) {
        return Purchase.builder()
            .customerId(customerId)
            .amount(amount)
            .build();
    }

    public static CreatePurchaseRequest prepareCreateRequest(BigDecimal amount, Long customerId) {
        return CreatePurchaseRequest.builder()
            .amount(amount)
            .customerId(customerId)
            .build();
    }

    public static UpdatePurchaseRequest prepareUpdateRequest(BigDecimal newValue) {
        return UpdatePurchaseRequest.builder()
            .newValue(newValue)
            .build();
    }
}
