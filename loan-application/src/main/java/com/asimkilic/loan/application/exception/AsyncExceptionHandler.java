package com.asimkilic.loan.application.exception;

import com.asimkilic.loan.application.twilio.TwilioSmsSender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger logger = LogManager.getLogger(AsyncExceptionHandler.class);
    //private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    // TODO : com.twilio.exception.ApiException daha spesifik handle et
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        //LOGGER.error("\n\t Exception: {}  \n\t  Method Name: {} \n\t  args: {}  ", ex, method.getName(), Arrays.toString(params));
        //logger.error("\n\t Exception: {}  \n\t  Method Name: {} \n\t  args: {}  ", ex, method.getName(), Arrays.toString(params));


        logger.error("Error Id = {} Exception in {}.{} with cause = {}, with message ={} params = {}",
                Arrays.asList(params).get(params.length - 1),
                method.getDeclaringClass().getName(),
                method.getName(),
                ex.getCause() != null ? ex.getCause() : "NULL",
                ex.getMessage() != null ? ex.getMessage() : "NULL",
                Arrays.toString(params)
        );
    }

}
