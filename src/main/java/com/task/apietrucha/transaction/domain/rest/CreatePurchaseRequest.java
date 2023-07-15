package com.task.apietrucha.transaction.domain.rest;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record CreatePurchaseRequest(
    @NotNull(message = "Amount cannot be null") BigDecimal amount,
    @NotNull(message = "Customer id cannot be null") Long customerId) {

}
