package com.asimkilic.loan.application.gen.service.turkishrepublicidno;

import com.asimkilic.loan.application.dto.customer.VerifyCustomerTurkishRepublicIdNoRequestDto;
import com.asimkilic.loan.application.gen.service.turkishrepublicidno.BaseTurkishRepublicIdNoVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.WS.KpsPublicSoapService;

@Service
@RequiredArgsConstructor
public class TrIdNoVerificationService implements BaseTurkishRepublicIdNoVerificationService {
   private final KpsPublicSoapService kpsPublicSoapService;

    @Value("${customer-turkish-republic-id-no-verify}")
    private boolean isVerifyActive;

    @Override
    public boolean verifyTurkishRepublicIdNo(VerifyCustomerTurkishRepublicIdNoRequestDto customer) {
        if (!isVerifyActive) {
            return true;
        }
        boolean isVerified = kpsPublicSoapService.verifyTurkishRepublicIdNo(
                customer.getTurkishRepublicIdentityNo(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getYearOfBirth());
        return isVerified;
    }





}
