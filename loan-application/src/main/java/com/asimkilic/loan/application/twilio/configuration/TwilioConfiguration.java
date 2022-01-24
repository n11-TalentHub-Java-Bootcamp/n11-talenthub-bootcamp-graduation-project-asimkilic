package com.asimkilic.loan.application.twilio.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {
    @Value("${twilio-account_sid}")
    private    String accountSid;
    @Value("${twilio-auth_token}")
    private   String authToken;
    @Value("${twilio-trial_number}")
    private   String trialNumber;

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setTrialNumber(String trialNumber) {
        this.trialNumber = trialNumber;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getTrialNumber() {
        return trialNumber;
    }
}
