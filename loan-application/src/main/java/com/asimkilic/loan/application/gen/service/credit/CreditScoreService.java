package com.asimkilic.loan.application.gen.service.credit;

import com.asimkilic.loan.application.dto.credit.CreditScoreInquiryRequestDto;
import com.asimkilic.loan.application.gen.service.credit.BaseCreditScoreInquiryService;
import com.creditscoreinquiry.CreditScoreInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreditScoreService implements BaseCreditScoreInquiryService {

    private final CreditScoreInquiryService creditScoreInquiryService;

    @Value("${customer-credit-score-generate-random}")
    private boolean isGenerateRandomActive;

    @Override
    public BigDecimal findCreditScore(CreditScoreInquiryRequestDto requestDto) {
        if (doYouHaveFriendAtCourt(requestDto.getTurkishRepublicIdNo())) {
            return BigDecimal.valueOf(1899);
        }
        if (isGenerateRandomActive) {
            return creditScoreInquiryService.calculateCreditScore(requestDto.getTurkishRepublicIdNo());
        }
        return BigDecimal.valueOf(400);
    }

    private boolean doYouHaveFriendAtCourt(String turkishRepublicIdNo) {
        return turkishRepublicIdNo.endsWith("12304");

    }
}
