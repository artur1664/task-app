package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.RestConstants.APP_BASE_URL_V1;

import com.task.apietrucha.transaction.application.contract.PurchaseController;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseResponse;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(APP_BASE_URL_V1)
@RestController
public class PurchaseControllerImpl implements PurchaseController {

    @Override
    public CreatePurchaseResponse create(@RequestBody CreatePurchaseRequest request) {
        log.info("Try to create new purchase: {}", request);
        throw new NotImplementedException("to implement");
    }

    @Override
    public UpdatePurchaseResponse update(
        @RequestBody UpdatePurchaseRequest request,
        @PathVariable Long purchaseId) {
        log.info("Try to update purchase: {}", request);
        throw new NotImplementedException("to implement");
    }
}
