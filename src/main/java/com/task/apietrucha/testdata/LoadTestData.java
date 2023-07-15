package com.task.apietrucha.testdata;

import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.entity.Points;
import com.task.apietrucha.transaction.domain.entity.Purchase;
import com.task.apietrucha.transaction.infrastructure.PointsRepository;
import com.task.apietrucha.transaction.infrastructure.PurchaseRepository;
import java.math.BigDecimal;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("test-data")
@Component
public class LoadTestData implements CommandLineRunner {

    @Autowired
    private PointsRepository pointsRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PointsService pointsService;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        log.warn("******** STARTING APPLICATION WITH TEST DATA ********");
        log.warn("******** STARTING APPLICATION WITH TEST DATA ********");
        log.warn("******** STARTING APPLICATION WITH TEST DATA ********");

        for (long i = 0; i < 10; i++) {
            for (long j = 0; j < 50; j++) {
                var amount = BigDecimal.valueOf(random.nextLong(10, 150));
                var purchase = purchaseRepository.save(Purchase.builder()
                    .customerId(i)
                    .amount(amount)
                    .build());
                pointsRepository.save(Points.builder()
                    .purchase(purchase)
                    .points(pointsService.calculate(amount))
                    .customerId(i)
                    .createdYear(2023)
                    .createdMonth(random.nextInt(1, 8))
                    .build());
            }
        }

        log.warn("******** TEST DATA INITIALIZED ********");
        log.warn("******** TEST DATA INITIALIZED ********");
        log.warn("******** TEST DATA INITIALIZED ********");
    }
}
