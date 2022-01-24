package com.asimkilic.loan.application;

import com.creditscoreinquiry.CreditScoreInquiryService;
import com.asimkilic.loan.application.twilio.configuration.TwilioConfiguration;
import com.squareup.okhttp.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import tr.gov.nvi.tckimlik.WS.KpsPublicSoapService;

import java.io.IOException;
import java.time.Clock;

@SpringBootApplication
@EnableAsync
public class LoanApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(LoanApplication.class, args);

    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public KpsPublicSoapService baseTurkishRepublicIdNoVerificationService() {
        return new KpsPublicSoapService();
    }

    @Bean
    public CreditScoreInquiryService creditScoreInquiryService() {
        return new CreditScoreInquiryService();
    }

}
