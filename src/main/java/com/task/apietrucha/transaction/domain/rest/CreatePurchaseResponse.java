package com.task.apietrucha.transaction.domain.rest;

import lombok.Builder;

@Builder
public record CreatePurchaseResponse(Long id) {

    public static CreatePurchaseResponse from(Long id) {
        return CreatePurchaseResponse.builder()
            .id(id)
            .build();
    }
}
