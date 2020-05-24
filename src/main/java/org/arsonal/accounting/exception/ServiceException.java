package org.arsonal.accounting.exception;

import lombok.Data;

/**
 * HC Accounting Service Exception
 */
@Data
public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode; // business error code;

    private ErrorType errorType;

    public enum ErrorType {
        Client,
        Service,
        Unknown;
    }

    public ServiceException(String message) {
        super(message);
    }
}
