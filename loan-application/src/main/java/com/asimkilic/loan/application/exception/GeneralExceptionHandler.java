package com.asimkilic.loan.application.exception;

import com.asimkilic.loan.application.exception.customer.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tr.gov.nvi.tckimlik.WS.exception.KpsServiceUnavailableException;


import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );
        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> customerNotFoundException(CustomerNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.httpStatus);
    }

    @ExceptionHandler(TurkishRepublicIdNoIsAlreadySavedException.class)
    public ResponseEntity<?> turkishRepublicIdNoIsAlreadySavedException(TurkishRepublicIdNoIsAlreadySavedException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.httpStatus);
    }

    @ExceptionHandler(EmailIsAlreadySavedException.class)
    public ResponseEntity<?> emailIsAlreadySavedException(EmailIsAlreadySavedException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.httpStatus);
    }


    @ExceptionHandler(PhoneIsAlreadySavedException.class)
    public ResponseEntity<?> phoneIsAlreadySavedException(PhoneIsAlreadySavedException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.httpStatus);
    }
    @ExceptionHandler(IllegalCustomerUpdateArgumentException.class)
    public ResponseEntity<?> illegalCustomerUpdateArgumentException(IllegalCustomerUpdateArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.httpStatus);
    }
    @ExceptionHandler(KpsServiceUnavailableException.class)
    public ResponseEntity<?> kpsServiceUnavailableException(KpsServiceUnavailableException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.httpStatus);
    }
}
