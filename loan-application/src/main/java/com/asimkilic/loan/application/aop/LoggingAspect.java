package com.asimkilic.loan.application.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Configuration
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("within(com.asimkilic.loan.application.controller.*)")
    public void controllerPackage() {

    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerBean() {

    }

    @Around("controllerBean() && controllerPackage()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("Request for {}.{}() with arguments[s]={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        Instant start = Instant.now();

        Object returnValue = joinPoint.proceed();

        Instant finish = Instant.now();
        Long timeElapsed = Duration.between(start, finish).toMillis();
        logger.debug("Response for {}.{} with Result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), returnValue);
        logger.info("Time Taken = " + new SimpleDateFormat("mm:ss:SSS").format(new Date(timeElapsed)));

        return returnValue;
    }

    @Pointcut("within(com.asimkilic.loan.application.controller.*)")
    public void exceptionPackage() {

    }


    @AfterThrowing(pointcut = "exceptionPackage()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {}.{} with cause = {}, with message ={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getCause() : "NULL",
                e.getMessage() != null ? e.getMessage() : "NULL"
        );
    }

}
