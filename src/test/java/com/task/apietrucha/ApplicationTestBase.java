package com.task.apietrucha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.apietrucha.transaction.infrastructure.PointsRepository;
import com.task.apietrucha.transaction.infrastructure.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTestBase {

    protected ObjectMapper mapper = new ObjectMapper();
    @Autowired
    protected MockMvc mockMvc;
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
