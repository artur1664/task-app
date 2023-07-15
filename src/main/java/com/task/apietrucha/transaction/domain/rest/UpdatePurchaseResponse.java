package com.task.apietrucha.transaction.domain.rest;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record UpdatePurchaseResponse(BigDecimal value) {

    public static UpdatePurchaseResponse from(BigDecimal value) {
        return UpdatePurchaseResponse.builder()
            .value(value)
            .build();
    }
}
