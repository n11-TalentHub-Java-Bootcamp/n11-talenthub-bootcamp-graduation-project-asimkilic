package com.asimkilic.loan.application.gen.service;

import java.math.BigDecimal;

public abstract class BaseCreditCalculationService {

    protected  BigDecimal clientSalary;
    protected  BigDecimal clientAmountofGuarantee;
    protected  BigDecimal creditLimit;
    protected  BigDecimal percentageOfGuarantee;
    protected  BigDecimal creditLimitMultiplierCoefficient;



    protected final BigDecimal calculateCredit() {
        BigDecimal salaryCoefficient = clientSalary.multiply(creditLimitMultiplierCoefficient);
        BigDecimal guaranteeCoefficient = percentageOfGuarantee.multiply(clientAmountofGuarantee.divide(BigDecimal.valueOf(100)));
        return creditLimit.add(salaryCoefficient).add(guaranteeCoefficient);
    }
}
