package com.asimkilic.loan.application.gen.service.credit;


import com.asimkilic.loan.application.dto.credit.CreditScoreInquiryRequestDto;

import java.math.BigDecimal;

public interface BaseCreditScoreInquiryService {
    BigDecimal findCreditScore(CreditScoreInquiryRequestDto requestDto);

}

