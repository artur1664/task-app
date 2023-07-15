package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.rest.RestConstants.APP_BASE_URL_V1;

import com.task.apietrucha.transaction.application.contract.RewordController;
import com.task.apietrucha.transaction.domain.adapter.RewordsService;
import com.task.apietrucha.transaction.domain.rest.RewordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(APP_BASE_URL_V1)
@RequiredArgsConstructor
@RestController
public class RewordControllerImpl implements RewordController {

    private final RewordsService service;

    @Override
    public RewordResponse getRewordInfo(@PathVariable Long customerId) {
        log.info("Try to get reword info. Customer id: {}", customerId);
        return service.getRewordInfo(customerId);
    }
}
