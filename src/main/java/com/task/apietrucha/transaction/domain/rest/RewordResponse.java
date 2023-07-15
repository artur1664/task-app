package com.task.apietrucha.transaction.domain.rest;

import lombok.Builder;

@Builder
public record RewordResponse(String reword) {

    public static RewordResponse from(String message) {
        return RewordResponse.builder()
            .reword(message)
            .build();
    }
}
