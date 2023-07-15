package com.task.apietrucha.transaction.domain.rest;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record UpdatePurchaseRequest(
    @NotNull(message = "New value is required") BigDecimal newValue) {

}
