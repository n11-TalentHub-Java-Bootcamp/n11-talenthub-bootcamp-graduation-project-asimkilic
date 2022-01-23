package com.asimkilic.loan.application.dto.credit;

import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Schema(description = "It is used to return approved application")
@Builder
public class ApprovedCreditResponse implements BaseCreditResponse {
    @Getter
    @Enumerated(EnumType.STRING)
    private final EnumCreditStatus response = EnumCreditStatus.APPROVED;

    @Getter
    @Setter
    private BigDecimal creditLimit;
}
