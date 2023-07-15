package com.task.apietrucha.transaction.application.contract;

import com.task.apietrucha.shared.ErrorResponse;
import com.task.apietrucha.transaction.domain.rest.PointsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Transaction")
public interface PointsController {

    String POINTS_GET_URL = "/points/customer/{customerId}";

    @PostMapping(POINTS_GET_URL)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get points from last three months")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User points per month",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PointsResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Data not found",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class))),
    })
    PointsResponse getPointsForUser(Long userId);
}
