package com.asimkilic.loan.application.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

import static com.asimkilic.loan.application.gen.message.InfoMessage.*;
import static com.asimkilic.loan.application.gen.message.InfoMessage.PHONE_NUMBER_FORMAT_MESSAGE;

@Data
@Schema(description = "It is used to update the Customer")
public class CustomerUpdateRequestDto {

    private String id;

    @Email
    private String email;


    @Positive
    private BigDecimal monthlySalary;

    @PositiveOrZero
    private BigDecimal amountOfGuarantee;



    @Pattern(regexp = "[1-9]+[0-9]*",
            message = PHONE_NUMBER_FORMAT_MESSAGE)
    @Schema(example = "5321231234")
    private String primaryPhone;


    @Pattern(regexp = "[1-9]+[0-9]*",
            message = PHONE_NUMBER_FORMAT_MESSAGE)
    @Schema(example = "5321231235")
    private String secondaryPhone;

}
