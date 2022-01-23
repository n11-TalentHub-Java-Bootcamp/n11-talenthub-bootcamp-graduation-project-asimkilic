package com.asimkilic.loan.application.dto.credit;

import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Schema(description = "It is used to return denied application")
public class DeniedCreditResponse implements BaseCreditResponse {

    @Getter
    @Enumerated(EnumType.STRING)
    private final EnumCreditStatus response = EnumCreditStatus.DENIED;

}
