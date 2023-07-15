package com.task.apietrucha.transaction.domain;

import static com.task.apietrucha.transaction.domain.adapter.RewordsService.BAD_CUSTOMER_MESSAGE;
import static com.task.apietrucha.transaction.domain.adapter.RewordsService.GOOD_CUSTOMER_MESSAGE;
import static com.task.apietrucha.transaction.domain.adapter.RewordsService.GREAT_CUSTOMER_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.task.apietrucha.transaction.domain.adapter.PointsService;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RewordsServiceImplTest {

    @Mock
    private PointsService pointsService;

    private RewordsServiceImpl rewordsService;

    @BeforeEach
    void setUp() {
        rewordsService = new RewordsServiceImpl(pointsService);
    }

    private static Stream<Arguments> reword_test_data() {
        return Stream.of(
            Arguments.of(null, BAD_CUSTOMER_MESSAGE),
            Arguments.of(0L, BAD_CUSTOMER_MESSAGE),
            Arguments.of(1L, GOOD_CUSTOMER_MESSAGE),
            Arguments.of(99L, GOOD_CUSTOMER_MESSAGE),
            Arguments.of(100L, GREAT_CUSTOMER_MESSAGE),
            Arguments.of(101L, GREAT_CUSTOMER_MESSAGE),
            Arguments.of(1000L, GREAT_CUSTOMER_MESSAGE)
        );
    }

    @ParameterizedTest
    @MethodSource("reword_test_data")
    void get_reword_info_should_return_response(Long customerPoints, String expectedRewordMessage) {
        //given
        Long customerId = 1L;
        when(pointsService.getTotalPointsForCustomer(customerId)).thenReturn(customerPoints);
        //when
        var result = rewordsService.getRewordInfo(customerId);
        //then
        assertEquals(expectedRewordMessage, result.reword());
    }
}