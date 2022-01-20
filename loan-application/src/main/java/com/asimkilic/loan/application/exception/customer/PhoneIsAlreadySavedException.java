package com.asimkilic.loan.application.exception.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PhoneIsAlreadySavedException extends RuntimeException {

    public PhoneIsAlreadySavedException(String message) {
        super(message);
    }

    public final HttpStatus httpStatus = HttpStatus.CONFLICT;
}
