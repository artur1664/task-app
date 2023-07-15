package com.task.apietrucha;

import com.task.apietrucha.configuration.TestTimeConfiguration;
import com.task.apietrucha.shared.DateTimeUtils;
import com.task.apietrucha.transaction.infrastructure.PointsRepository;
import com.task.apietrucha.transaction.infrastructure.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("it")
@Import({TestTimeConfiguration.class, DateTimeUtils.class})
@DataJpaTest
public class DbTestBase {

    @Autowired
    protected PointsRepository pointsRepository;

    @Autowired
    protected PurchaseRepository purchaseRepository;

    @Autowired
    protected DateTimeUtils dateTimeUtils;

    @BeforeEach
    void setUp() {
        pointsRepository.deleteAll();
        purchaseRepository.deleteAll();
    }
}
