package com.asimkilic.loan.application;

import com.asimkilic.loan.application.dto.customer.KpsPublicVerifyCustomerRequestDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tr.gov.nvi.tckimlik.WS.KpsPublicSoapService;

import java.time.Clock;

@SpringBootApplication
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
		KpsPublicSoapService sp = new KpsPublicSoapService();
		KpsPublicVerifyCustomerRequestDto dto = new KpsPublicVerifyCustomerRequestDto();
		dto.setTurkishRepublicIdentityNo("13390212304");
		dto.setFirstName("ABDULLAH ASIM");
		dto.setLastName("KILIÇ");
		dto.setYearOfBirth("1992");
		var r = sp.verifyTurkishRepublicIdNo(dto);
	}


	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}

}
