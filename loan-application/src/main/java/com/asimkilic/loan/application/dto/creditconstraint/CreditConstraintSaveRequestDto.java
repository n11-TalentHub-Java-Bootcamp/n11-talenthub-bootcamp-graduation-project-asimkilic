package com.asimkilic.loan.application.dto.creditconstraint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

import static com.asimkilic.loan.application.gen.message.InfoMessage.CREDIT_LIMIT_CANNOT_BE_NULL;
@Data
@Schema(description = "It is used to save new Credit Constraint")
public class CreditConstraintSaveRequestDto {


    @NotNull(message = CREDIT_LIMIT_CANNOT_BE_NULL)
    @PositiveOrZero
    private BigDecimal creditLimit;

    @Min(0)
    @Max(100)
    @NotNull
    @Schema(example = "15.00")
    private BigDecimal percentageOfGuarantee;

    @NotNull
    @PositiveOrZero
    @Schema(example = "0.5")
    private BigDecimal creditLimitMultiplierCoefficient;


    @NotNull
    @PositiveOrZero
    @Schema(example = "500")
    private BigDecimal creditScoreLowerLimit;

    @NotNull
    @PositiveOrZero
    @Schema(example = "1000")
    private BigDecimal creditScoreUpperLimit;

    @PositiveOrZero
    @Schema(example = "5000")
    private BigDecimal salaryLowerLimit;


    @Positive
    @Schema(example = "10000")
    private BigDecimal salaryUpperLimit;
}
