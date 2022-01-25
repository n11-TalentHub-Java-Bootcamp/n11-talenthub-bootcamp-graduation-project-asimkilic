package com.asimkilic.loan.application.dto.credit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.asimkilic.loan.application.gen.message.InfoMessage.CUSTOMER_TR_ID_NO_DESCRIPTION;


@Data
@Schema(description = "It is used to query credit application")
public class CreditResultRequestDto {

    @NotNull(message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Size(min = 11, max = 11, message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Schema(description = CUSTOMER_TR_ID_NO_DESCRIPTION, example = "12345678912")
    private String turkishRepublicIdNo;


    @NotNull
    @Schema(example = "1912-06-23", type = "string", format = "date")
    private Date dateOfBirth;

}
