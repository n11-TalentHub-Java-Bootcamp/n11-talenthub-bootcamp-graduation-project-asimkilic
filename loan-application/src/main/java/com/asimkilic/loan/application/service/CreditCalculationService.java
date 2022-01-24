package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.dto.credit.CreditCalculationRequestDto;
import com.asimkilic.loan.application.gen.service.credit.BaseCreditCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.asimkilic.loan.application.gen.message.InfoMessage.CREDIT_CALCULATOR_PARAMETERS_ARE_NULL;

@Service
class CreditCalculationService extends BaseCreditCalculationService {


    public BigDecimal calculate(CreditCalculationRequestDto requestDto) {
        if (requestDto == null) {
            throw new RuntimeException(CREDIT_CALCULATOR_PARAMETERS_ARE_NULL);
        }
        super.clientSalary = requestDto.getClientSalary();
        super.clientAmountOfGuarantee = requestDto.getClientAmountOfGuarantee();
        super.creditLimit = requestDto.getCreditLimit();
        super.creditLimitMultiplierCoefficient = requestDto.getCreditLimitMultiplierCoefficient();
        super.percentageOfGuarantee = requestDto.getPercentageOfGuarantee();

        return super.calculateCredit();

    }
}
