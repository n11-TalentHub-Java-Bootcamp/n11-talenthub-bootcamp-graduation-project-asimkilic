package com.asimkilic.loan.application.exception;

import com.asimkilic.loan.application.exception.credit.CreditNotFoundException;
import com.asimkilic.loan.application.exception.customer.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tr.gov.nvi.tckimlik.WS.exception.KpsServiceUnavailableException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        HttpServletRequest req = ((ServletWebRequest) request).getRequest();
        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(req.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(status.value())
                .build();

        return new ResponseEntity<Object>(errorResponse, headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());
        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(ResponseStatusException ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getReason());

        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.getStatus().value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex, HttpServletRequest request) {
        List<String> errors = List.of(ex.getMessage());
        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.httpStatus.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.httpStatus);
    }

    @ExceptionHandler(TurkishRepublicIdNoIsAlreadySavedException.class)
    public ResponseEntity<ErrorResponse> handleTurkishRepublicIdNoIsAlreadySavedException(TurkishRepublicIdNoIsAlreadySavedException ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.httpStatus.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.httpStatus);
    }

    @ExceptionHandler(EmailIsAlreadySavedException.class)
    public ResponseEntity<ErrorResponse> handleEmailIsAlreadySavedException(EmailIsAlreadySavedException ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.httpStatus.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.httpStatus);
    }

    @ExceptionHandler(PhoneIsAlreadySavedException.class)
    public ResponseEntity<ErrorResponse> handlePhoneIsAlreadySavedException(PhoneIsAlreadySavedException ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.httpStatus.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.httpStatus);
    }

    @ExceptionHandler(IllegalCustomerUpdateArgumentException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(IllegalCustomerUpdateArgumentException ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());
        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.httpStatus.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.httpStatus);
    }

    @ExceptionHandler(KpsServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleKpsServiceUnavailableException(KpsServiceUnavailableException ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.httpStatus.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.httpStatus);
    }

    @ExceptionHandler(CreditNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCreditNotFoundException(CreditNotFoundException ex, HttpServletRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponseBuilder
                .getInstance()
                .withErrorId("LoanApplication-" + LocalDateTime.now(ZoneOffset.UTC))
                .withPath(request.getRequestURI())
                .withErrors(errors)
                .withMessage(ex.getMessage())
                .withStatus(ex.httpStatus.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, ex.httpStatus);
    }

}
