package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.RestConstants.APP_BASE_URL_V1;

import com.task.apietrucha.transaction.application.contract.PointsController;
import com.task.apietrucha.transaction.domain.rest.PointsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(APP_BASE_URL_V1)
@RestController
public class PointsControllerImpl implements PointsController {

    @Override
    public PointsResponse getPointsForUser(Long userId) {
        throw new NotImplementedException("to implement");
    }
}
