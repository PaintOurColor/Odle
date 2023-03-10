package com.paintourcolor.odle.util.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class RestApiException {
    private String errorMessage;
    private HttpStatus httpStatus;
    private String errorCode;
}
