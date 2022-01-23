package com.asimkilic.loan.application.dto.credit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "It is used to request to calculate credit limit")
@Builder
public class CreditCalculationRequestDto {
    private  BigDecimal clientSalary;
    private  BigDecimal clientAmountofGuarantee;
    private  BigDecimal creditLimit;
    private  BigDecimal percentageOfGuarantee;
    private  BigDecimal creditLimitMultiplierCoefficient;
}
