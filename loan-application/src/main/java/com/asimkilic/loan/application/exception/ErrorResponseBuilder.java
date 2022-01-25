package com.asimkilic.loan.application.exception;

import java.util.List;

public class ErrorResponseBuilder {
    private String errorId;
    private Integer status;
    private String message;
    private List<String> errors;
    private String path;

    private ErrorResponseBuilder() {
    }

    private static final class BuilderHolder {
        private static final ErrorResponseBuilder builder = new ErrorResponseBuilder();
    }

    public static ErrorResponseBuilder getInstance() {
        return BuilderHolder.builder;
    }

    public ErrorResponseBuilder withErrorId(String errorId) {
        BuilderHolder.builder.errorId = errorId;
        return BuilderHolder.builder;
    }

    public ErrorResponseBuilder withStatus(Integer status) {
        BuilderHolder.builder.status = status;
        return BuilderHolder.builder;
    }

    public ErrorResponseBuilder withMessage(String message) {
        BuilderHolder.builder.message = message;
        return BuilderHolder.builder;
    }

    public ErrorResponseBuilder withErrors(List<String> errors) {
        BuilderHolder.builder.errors = errors;
        return BuilderHolder.builder;
    }

    public ErrorResponseBuilder withPath(String path) {
        BuilderHolder.builder.path = path;
        return BuilderHolder.builder;
    }

    public ErrorResponse build() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorId(BuilderHolder.builder.errorId);
        errorResponse.setErrors(BuilderHolder.builder.errors);
        errorResponse.setMessage(BuilderHolder.builder.message);
        errorResponse.setPath(BuilderHolder.builder.path);
        errorResponse.setStatus(BuilderHolder.builder.status);
        return errorResponse;
    }
}
