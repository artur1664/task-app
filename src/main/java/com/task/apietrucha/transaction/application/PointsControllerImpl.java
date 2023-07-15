package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.rest.RestConstants.APP_BASE_URL_V1;

import com.task.apietrucha.transaction.application.contract.PointsController;
import com.task.apietrucha.transaction.domain.adapter.PointsService;
import com.task.apietrucha.transaction.domain.rest.PointsResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(APP_BASE_URL_V1)
@RequiredArgsConstructor
@RestController
public class PointsControllerImpl implements PointsController {

    private final PointsService pointsService;

    @Override
    public PointsResponse getPointsForCustomer(@PathVariable @NotNull Long customerId) {
        log.info("Try to get customer points from last 3 months. Id: {}", customerId);
        return pointsService.getPointsFromLastThreeMonths(customerId);
    }
}
