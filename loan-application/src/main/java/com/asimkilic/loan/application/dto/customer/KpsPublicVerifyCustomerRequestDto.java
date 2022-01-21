package com.asimkilic.loan.application.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "It is used to verify customer from government system")
public class KpsPublicVerifyCustomerRequestDto {

    private String turkishRepublicIdentityNo;

    private String firstName;

    private String lastName;

    private String yearOfBirth;

}
