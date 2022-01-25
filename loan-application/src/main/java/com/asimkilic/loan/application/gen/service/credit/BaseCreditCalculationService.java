package com.asimkilic.loan.application.gen.service.credit;

import com.asimkilic.loan.application.dto.credit.CreditCalculationRequestDto;

import java.math.BigDecimal;

public abstract class BaseCreditCalculationService {

    protected BigDecimal clientSalary;
    protected BigDecimal clientAmountOfGuarantee;
    protected BigDecimal creditLimit;
    protected BigDecimal percentageOfGuarantee;
    protected BigDecimal creditLimitMultiplierCoefficient;
    // TODO : varsayılan olarak 4 değişirse sorun olacaktır bunu dinamik hale getir.
    protected final BigDecimal creditLimitMultiplierConstant = BigDecimal.valueOf(4);

    public abstract BigDecimal calculate(CreditCalculationRequestDto creditCalculationRequestDto);

    /***
     * Formula : (customerSalary * creditLimitMultiplier) + (creditLimit) + (customerGuarantee * percentageOfGuarantee / 100)
     * Constant of credit multiplier is 4.
     * @return total credit amount.
     */
    protected final BigDecimal calculateCredit() {
        BigDecimal creditLimitMultiplier = creditLimitMultiplierConstant.multiply(creditLimitMultiplierCoefficient);
        BigDecimal salaryCoefficient = clientSalary.multiply(creditLimitMultiplier);
        BigDecimal guaranteeCoefficient = percentageOfGuarantee.multiply(clientAmountOfGuarantee.divide(BigDecimal.valueOf(100)));
        return creditLimit.add(salaryCoefficient).add(guaranteeCoefficient);
    }
}
