package com.task.apietrucha.transaction.domain.adapter;

import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseResponse;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseResponse;

public interface PurchaseService {

    CreatePurchaseResponse create(CreatePurchaseRequest request);

    UpdatePurchaseResponse update(UpdatePurchaseRequest request, Long purchaseId);

}
