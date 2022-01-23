package com.asimkilic.loan.application.dto.credit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "It is used to inquiry the credit score")
@Builder
public class CreditScoreInquiryRequestDto {

    private String turkishRepublicIdNo;


}
