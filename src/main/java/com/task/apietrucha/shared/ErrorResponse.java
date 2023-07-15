package com.task.apietrucha.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    @JsonProperty("timestamp")
    private String timeStamp;
}
