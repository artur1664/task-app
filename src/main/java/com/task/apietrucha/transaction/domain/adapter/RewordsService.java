package com.task.apietrucha.transaction.domain.adapter;

import com.task.apietrucha.transaction.domain.rest.RewordResponse;

public interface RewordsService {

    String BAD_CUSTOMER_MESSAGE = "Bad, bad customer!!! You dont have points!!! buy something !!!";
    String GOOD_CUSTOMER_MESSAGE = "Hmm you have some points but you can do better :) we will give you something";
    String GREAT_CUSTOMER_MESSAGE = "Well hello :) you have many points car is waiting for you !!!";

    RewordResponse getRewordInfo(Long customerId);
}
