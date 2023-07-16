package com.task.apietrucha.transaction.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.task.apietrucha.DbTestBase;
import com.task.apietrucha.factory.PurchaseTestDataFactory;
import com.task.apietrucha.shared.DateTimeUtils;
import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.adapter.PurchaseService;
import com.task.apietrucha.transaction.domain.entity.Points;
import com.task.apietrucha.transaction.domain.entity.Purchase;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class PurchaseServiceImplIT extends DbTestBase {

    private PurchaseService service;

    @MockBean
    private PointsService pointsService;

    @MockBean
    private DateTimeUtils dateTimeUtils;

    @BeforeEach
    void setUp() {
        service = new PurchaseServiceImpl(purchaseRepository, pointsRepository, pointsService, dateTimeUtils);
    }

    @Test
    void update_should_update_purchase_and_create_points_if_not_present() {
        //given
        Purchase purchase = purchaseRepository.save(PurchaseTestDataFactory.prepare(BigDecimal.valueOf(200), 1L).build());
        //when
        var result = service.update(PurchaseTestDataFactory.prepareUpdateRequest(BigDecimal.valueOf(150)).build(), purchase.getId());
        //then
        assertEquals(0, purchase.getAmount().compareTo(result.value()));
        //and points created
        Optional<Points> savedPoints = pointsRepository.findByPurchase(purchase);
        assertTrue(savedPoints.isPresent());
        //and purchase updated
        Optional<Purchase> updatedPurchase = purchaseRepository.findById(purchase.getId());
        assertTrue(updatedPurchase.isPresent());
        assertEquals(0, BigDecimal.valueOf(150).compareTo(updatedPurchase.get().getAmount()));
    }
}