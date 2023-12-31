package com.task.apietrucha.transaction.application.contract;

import com.task.apietrucha.shared.ErrorResponse;
import com.task.apietrucha.transaction.domain.rest.RewordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Transaction")
public interface RewordController {

    String REWORD_URL = "/reword/{customerId}";

    @GetMapping(REWORD_URL)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get reword information for customer")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reword information for customer",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = RewordResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
    })
    RewordResponse getRewordInfo(Long customerId);
}
