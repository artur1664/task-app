package com.task.apietrucha;

import com.task.apietrucha.transaction.infrastructure.PointsRepository;
import com.task.apietrucha.transaction.infrastructure.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DbTestBase {

    @Autowired
    protected PointsRepository pointsRepository;

    @Autowired
    protected PurchaseRepository purchaseRepository;

    @BeforeEach
    void setUp() {
        pointsRepository.deleteAll();
        purchaseRepository.deleteAll();
    }
}
