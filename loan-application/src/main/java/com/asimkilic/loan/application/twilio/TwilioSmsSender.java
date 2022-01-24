package com.asimkilic.loan.application.twilio;

import com.asimkilic.loan.application.gen.service.notification.sms.SmsRequest;
import com.asimkilic.loan.application.gen.service.notification.sms.SmsSender;
import com.asimkilic.loan.application.twilio.configuration.TwilioConfiguration;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
    private final TwilioConfiguration twilioConfiguration;

    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = smsRequest.getMessage();
        MessageCreator creator = Message.creator(to, from, message);

        creator.create();


    }
}
