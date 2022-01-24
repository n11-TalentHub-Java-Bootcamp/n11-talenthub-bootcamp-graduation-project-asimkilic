package com.asimkilic.loan.application.gen.service.notification.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SmsHandler {
    @Qualifier("twilio")
    private final SmsSender smsSender;

    @Async
    public void sendSms(String phone, String message, BigDecimal creditLimit) {
        // TODO concat stringbuffer ile yapılabilir.
        smsSender.sendSms(new SmsRequest(phone, message + " Credit limit is : " + creditLimit.toString()));

    }

    @Async
    public void sendSms(String phone, String message) {
        smsSender.sendSms(new SmsRequest(phone, message));
    }


}
