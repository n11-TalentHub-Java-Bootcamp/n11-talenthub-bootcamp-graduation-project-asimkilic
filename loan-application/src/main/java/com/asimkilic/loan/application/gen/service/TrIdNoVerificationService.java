package com.asimkilic.loan.application.gen.service;

import com.asimkilic.loan.application.dto.customer.VerifyCustomerTurkishRepublicIdNoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TrIdNoVerificationService {
    private final BaseTurkishRepublicIdNoVerificationService verificationService;

    @Value("${customer-turkish-republic-id-no}")
    private String demoId;

    public boolean verifyTurkishRepublicIdNo(VerifyCustomerTurkishRepublicIdNoRequestDto customer) {
        if (isDemoIdNo(customer.getTurkishRepublicIdentityNo())) {
            return true;
        }
        boolean isVerified = verificationService.verifyTurkishRepublicIdNo(
                customer.getTurkishRepublicIdentityNo(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getYearOfBirth());
        return isVerified;
    }

    private boolean isDemoIdNo(String idNo) {
        return Objects.equals(idNo, demoId);
    }
}
