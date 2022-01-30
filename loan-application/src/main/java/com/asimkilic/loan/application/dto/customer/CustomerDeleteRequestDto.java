package com.asimkilic.loan.application.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.asimkilic.loan.application.gen.message.InfoMessage.CUSTOMER_TR_ID_NO_DESCRIPTION;

@Data
@Schema(description = "It is used to delete customer")
public class CustomerDeleteRequestDto {

    //@NotBlank(message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @NotNull(message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Size(min = 11, max = 11, message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    @Schema(description = CUSTOMER_TR_ID_NO_DESCRIPTION,example = "12345678912")
    private String turkishRepublicIdNo;

}
