package com.asimkilic.loan.application.gen.service.turkishrepublicidno;

import com.asimkilic.loan.application.dto.customer.VerifyCustomerTurkishRepublicIdNoRequestDto;

public interface BaseTurkishRepublicIdNoVerificationService {
    boolean verifyTurkishRepublicIdNo(VerifyCustomerTurkishRepublicIdNoRequestDto customer);

}
