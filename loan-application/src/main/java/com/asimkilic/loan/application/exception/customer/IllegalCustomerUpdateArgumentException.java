package com.asimkilic.loan.application.exception.customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalCustomerUpdateArgumentException extends RuntimeException{

    public IllegalCustomerUpdateArgumentException(String message) {
        super(message);
    }

    public final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
}
