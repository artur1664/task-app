package com.task.apietrucha.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorResponse implements Serializable {

    private int status;
    private String error;
    private String message;
    @JsonProperty("timestamp")
    private String timeStamp;

    public static ErrorResponse from(String msg, int status, String error, String time) {
        return ErrorResponse.builder()
            .status(status)
            .error(error)
            .message(msg)
            .timeStamp(time)
            .build();
    }

    public static ErrorResponse unexpected() {
        return ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("Unexpected server error")
            .timeStamp(OffsetDateTime.now().toString())
            .build();
    }
}
