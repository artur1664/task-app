package com.task.apietrucha.transaction.domain;

import com.task.apietrucha.transaction.application.PurchaseNotFound;
import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.adapter.PurchaseService;
import com.task.apietrucha.transaction.domain.entity.Points;
import com.task.apietrucha.transaction.domain.entity.Purchase;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.CreatePurchaseResponse;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseRequest;
import com.task.apietrucha.transaction.domain.rest.UpdatePurchaseResponse;
import com.task.apietrucha.transaction.infrastructure.PointsRepository;
import com.task.apietrucha.transaction.infrastructure.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PointsRepository pointsRepository;
    private final PointsService pointsService;

    @Transactional
    @Override
    public CreatePurchaseResponse create(CreatePurchaseRequest request) {
        log.info("Creating new purchase");
        Purchase savedPurchase = purchaseRepository.save(Purchase.from(request));
        int pointsForPurchase = pointsService.calculate(request.amount());
        Points points = pointsRepository.save(Points.from(pointsForPurchase, savedPurchase, request.customerId()));
        log.info("Purchase saved {}, points: {}", savedPurchase, points);
        return CreatePurchaseResponse.from(savedPurchase.getId());
    }

    @Transactional
    @Override
    public UpdatePurchaseResponse update(UpdatePurchaseRequest request, Long purchaseId) {
        log.info("Updating purchase info");
        return purchaseRepository.findById(purchaseId)
            .map(purchase -> {
                purchase.setAmount(request.newValue());
                return purchase;
            }).map(updatedPurchase -> {
                int calculatedPoints = pointsService.calculate(request.newValue());
                Points points = updateOrCreatePoints(updatedPurchase, calculatedPoints);
                log.info("Purchase updated {}, points: {}", updatedPurchase, points);
                return UpdatePurchaseResponse.from(updatedPurchase.getAmount());
            })
            .orElseThrow(() -> new PurchaseNotFound("Purchase not found. Id: " + purchaseId));
    }

    private Points updateOrCreatePoints(Purchase purchase, int recalculatedPoints) {
        return pointsRepository.findByPurchase(purchase)
            .map(points -> {
                points.setPoints(recalculatedPoints);
                return points;
            })
            .orElseGet(
                () -> pointsRepository.save(Points.from(recalculatedPoints, purchase, purchase.getCustomerId()))
            );
    }
}
