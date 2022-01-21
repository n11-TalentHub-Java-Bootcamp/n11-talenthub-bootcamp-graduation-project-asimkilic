package com.asimkilic.loan.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tr.gov.nvi.tckimlik.WS.KpsPublicSoapService;

import java.time.Clock;

@SpringBootApplication
public class LoanApplication {

	public static void main(String[] args) {SpringApplication.run(LoanApplication.class, args);}


	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}
	@Bean
	public KpsPublicSoapService kpsPublicSoapService(){
		return new KpsPublicSoapService();
	}
}
