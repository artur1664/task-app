package com.task.apietrucha.transaction.application.contract;

import com.task.apietrucha.shared.ErrorResponse;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseResponse;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Transaction")
public interface PurchaseController {

    String PURCHASE_CREATE_URL = "/purchase";
    String PURCHASE_UPDATE_URL = "/purchase/{purchaseId}";

    @PostMapping(PURCHASE_CREATE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new purchase")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Purchase successfully created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CreatePurchaseRequest.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
    })
    CreatePurchaseResponse create(CreatePurchaseRequest request);

    @PutMapping(PURCHASE_UPDATE_URL)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update purchase")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Purchase successfully updated",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UpdatePurchaseRequest.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
    })
    UpdatePurchaseResponse update(UpdatePurchaseRequest request, Long purchaseId);
}