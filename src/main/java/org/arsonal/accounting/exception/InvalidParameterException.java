package org.arsonal.accounting.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException {
    /**
     * constructor for invalidParameterException.
     * @param message thrown message.
     */
    public InvalidParameterException(String message) {
        super(message);
        this.setErrorCode("INVALID_PARAMETER");
        this.setErrorType(ErrorType.Client);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
    }
}
