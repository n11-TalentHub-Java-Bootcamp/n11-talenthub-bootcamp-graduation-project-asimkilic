package com.asimkilic.loan.application.twilio;

import com.asimkilic.loan.application.twilio.configuration.TwilioConfiguration;
import com.twilio.Twilio;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {
    private final TwilioConfiguration twilioConfiguration;

    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );

    }
}
