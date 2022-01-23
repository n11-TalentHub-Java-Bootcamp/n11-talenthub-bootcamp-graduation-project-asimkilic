package com.creditscoreinquiry;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class CreditScoreInquiryService {

    public BigDecimal calculateCreditScore(String turkishRepublicIdNo) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 1901);
        return BigDecimal.valueOf(randomNum);

    }
}
