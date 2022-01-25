package com.asimkilic.loan.application.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private String errorId;
    private Integer status;
    private String message;
    private List<String> errors;
    private String path;

}
