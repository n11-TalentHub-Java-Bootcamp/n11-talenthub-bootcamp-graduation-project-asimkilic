package com.asimkilic.loan.application.dto.creditconstraint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "It is used to view the Credit Constaint")
public class CreditConstraintDto {

    private String id;

    private BigDecimal creditLimit;

    private BigDecimal percentageOfGuarantee;


    private BigDecimal creditLimitMultiplierCoefficient;

    private BigDecimal creditScoreLowerLimit;

    private BigDecimal creditScoreUpperLimit;

    private BigDecimal salaryLowerLimit;

    private BigDecimal salaryUpperLimit;
}
