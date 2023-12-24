package com.project.shopapp.exception;

import com.project.shopapp.model.ApiException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiRequestException extends RuntimeException {
    HttpStatus httpStatus;
    ApiException exception;

    public static ApiRequestException badRequest(List<String> message) {
        return ApiRequestException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exception(ApiException.builder()
                        .timestamp(ZonedDateTime.now(ZoneId.of("UTC")))
                        .status(HttpStatus.BAD_REQUEST)
                        .error(message)
                        .path("")
                        .build())
                .build();
    }

}
