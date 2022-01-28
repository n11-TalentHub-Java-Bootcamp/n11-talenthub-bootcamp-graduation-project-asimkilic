package com.asimkilic.loan.application.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

import static com.asimkilic.loan.application.gen.message.InfoMessage.*;

@Data
@Schema(description = "It is used to save new customer")
@Builder
public class CustomerSaveRequestDto {


    @NotNull(message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Size(min = 11, max = 11, message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    @Schema(description = CUSTOMER_TR_ID_NO_DESCRIPTION,example = "12345678912")
    private String turkishRepublicIdNo;

    @NotNull
    @Size(max = 100)
    @Schema(example = "Alan")
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Schema(example = "Turing")
    private String lastName;


    @Schema(example = "1912-06-23",type = "string",format = "date")
    private Date dateOfBirth;

    @Email
    @Schema(example = "alanturing@yahoo.com")
    private String email;

    @NotNull
    @Positive
    private BigDecimal monthlySalary;


    @PositiveOrZero
    private BigDecimal amountOfGuarantee;

    @NotNull(message = PHONE_NUMBER_CANNOT_BE_NULL)
    @Pattern(regexp = "^(5)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
            message = PHONE_NUMBER_FORMAT_MESSAGE)
    @Schema(example = "5321231234")
    private String primaryPhone;


    @Pattern(regexp = "^(5)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
            message = PHONE_NUMBER_FORMAT_MESSAGE)
    @Schema(example = "5321231235")
    private String secondaryPhone;



}
