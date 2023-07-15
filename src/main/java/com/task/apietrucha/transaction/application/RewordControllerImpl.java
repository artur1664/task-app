package com.task.apietrucha.transaction.application;

import static com.task.apietrucha.shared.rest.RestConstants.APP_BASE_URL_V1;

import com.task.apietrucha.transaction.application.contract.RewordController;
import com.task.apietrucha.transaction.domain.rest.RewordResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(APP_BASE_URL_V1)
@RestController
public class RewordControllerImpl implements RewordController {

    @Override
    public RewordResponse getReword(@RequestParam Integer points) {
        log.info("Try to get reword info");
        throw new NotImplementedException("to implement");
    }
}
