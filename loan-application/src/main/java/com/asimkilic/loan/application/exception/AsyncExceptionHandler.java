package com.asimkilic.loan.application.exception;

import com.asimkilic.loan.application.twilio.TwilioSmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    // TODO : com.twilio.exception.ApiException daha spesifik handle et
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        LOGGER.error("\n\t Exception: {}  \n\t  Method Name: {} \n\t  args: {}  ", ex, method.getName(), Arrays.toString(params));
    }
}
