package com.task.apietrucha.transaction.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.apietrucha.shared.DateTimeUtils;
import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.infrastructure.PointsRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PointsServiceImplTest {

    private PointsService service;

    @Mock
    private PointsRepository pointsRepository;

    @Mock
    private DateTimeUtils dateTimeUtils;

    @BeforeEach
    void setUp() {
        service = new PointsServiceImpl(pointsRepository, dateTimeUtils);
    }

    private static Stream<Arguments> calculate_test_case() {
        return Stream.of(
            Arguments.of(null, 0),
            Arguments.of(BigDecimal.ZERO, 0),
            Arguments.of(BigDecimal.ONE, 0),
            Arguments.of(BigDecimal.valueOf(-120), 0),
            Arguments.of(BigDecimal.valueOf(49), 0),
            Arguments.of(BigDecimal.valueOf(50), 0),
            Arguments.of(BigDecimal.valueOf(51), 1),
            Arguments.of(BigDecimal.valueOf(99), 49),
            Arguments.of(BigDecimal.valueOf(100), 50),
            Arguments.of(BigDecimal.valueOf(101), 53),
            Arguments.of(BigDecimal.valueOf(120), 110),// I think there is mistake in task description. Please check description.md
            Arguments.of(BigDecimal.valueOf(500), 450 + 2 * 400)
        );
    }

    @ParameterizedTest
    @MethodSource("calculate_test_case")
    void calculate_should_return_correct_result(BigDecimal input, Integer expectedResult) {
        //when
        Integer result = service.calculate(input);
        //then
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> get_points_test_case() {
        return Stream.of(
            Arguments.of(1, 2023, List.of(2023, 2022), List.of(12, 11, 1)),
            Arguments.of(2, 2023, List.of(2023, 2022), List.of(12, 2, 1)),
            Arguments.of(3, 2023, List.of(2023), List.of(3, 2, 1)),
            Arguments.of(4, 2023, List.of(2023), List.of(4, 3, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("get_points_test_case")
    void get_points_from_last_three_months_should_handle_data_from_previous_year(int actualMonth, int actualYear,
        List<Integer> years, List<Integer> months) {
        //given
        when(dateTimeUtils.getActualMonthNumber()).thenReturn(actualMonth);
        when(dateTimeUtils.getActualYearNumber()).thenReturn(actualYear);
        //
        var customerId = 1L;
        //when
        service.getPointsFromLastThreeMonths(customerId);
        //then
        verify(pointsRepository, times(1)).findPointsForCustomerByYearAndMonth(eq(customerId), eq(years), eq(months));
    }
}