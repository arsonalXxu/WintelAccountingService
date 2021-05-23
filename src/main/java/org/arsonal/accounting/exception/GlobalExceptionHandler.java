package org.arsonal.accounting.exception;

import lombok.val;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleServiceException(ServiceException e) {
        val errorResponse = ErrorResponse.builder()
            .errorCode(e.getErrorCode())
            .statusCode(e.getStatusCode())
            .errorType(e.getErrorType())
            .message(e.getMessage())
            .build();

        return ResponseEntity.status(e.getStatusCode() != 0 ? HttpStatus.INTERNAL_SERVER_ERROR.value() :
            e.getStatusCode())
            .body(errorResponse);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    ResponseEntity<?> handleIncorrectCredentialsException(IncorrectCredentialsException e) {
        val errorResponse = ErrorResponse.builder()
            .errorCode("INCORRECT_CREDENTIAL")
            .statusCode(400)
            .errorType(ServiceException.ErrorType.Client)
            .message(e.getMessage())
            .build();

        return ResponseEntity.status(400)
            .body(errorResponse);
    }
}
