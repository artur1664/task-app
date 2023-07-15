package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.RestConstants.APP_BASE_URL_V1;

import com.task.apietrucha.shared.ErrorResponse;
import com.task.apietrucha.transaction.application.contract.PurchaseController;
import com.task.apietrucha.transaction.domain.adapter.PurchaseService;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseResponse;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseResponse;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(APP_BASE_URL_V1)
@RestController
public class PurchaseControllerImpl implements PurchaseController {

    private final PurchaseService purchaseService;

    @Override
    public CreatePurchaseResponse create(@Valid @RequestBody CreatePurchaseRequest request) {
        log.info("Try to create new purchase: {}", request);
        return purchaseService.create(request);
    }

    @Override
    public UpdatePurchaseResponse update(
        @Valid @RequestBody UpdatePurchaseRequest request,
        @PathVariable Long purchaseId) {
        log.info("Try to update purchase: {}", request);
        return purchaseService.update(request, purchaseId);
    }

    @ExceptionHandler(PurchaseNotFound.class)
    public ResponseEntity<ErrorResponse> handlePurchaseNotFound(PurchaseNotFound ex) {
        log.error("Failed to find purchase: ", ex);
        return new ResponseEntity<>(ErrorResponse.from(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            ex.getClass().getName(),
            OffsetDateTime.now().toString()
        ), HttpStatus.NOT_FOUND);
    }

}
