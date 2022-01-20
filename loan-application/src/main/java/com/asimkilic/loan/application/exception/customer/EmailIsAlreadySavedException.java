package com.asimkilic.loan.application.exception.customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailIsAlreadySavedException extends RuntimeException {
    public EmailIsAlreadySavedException(String message) {
        super(message);
    }

    public final HttpStatus httpStatus = HttpStatus.CONFLICT;
}
