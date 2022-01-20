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

    @Size(min = 11, max = 11, message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Schema(description = CUSTOMER_TR_ID_NO_DESCRIPTION)
    private String turkishRepublicIdNo;

    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @Past
    private Date dateOfBirth;

    @Email
    private String email;

    @Min(value = 0)
    private BigDecimal monthlySalary;

    private BigDecimal amountOfGuarantee;

    @NotNull(message = PHONE_NUMBER_CANNOT_NULL)
    @Pattern(regexp = "(?:(?:(\\s*\\(?([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\)?\\s*(?:[.-]\\s*)?)([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})",
            message = PHONE_NUMBER_FORMAT_MESSAGE)
    private String primaryPhone;

    @Pattern(regexp = "(?:(?:(\\s*\\(?([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\)?\\s*(?:[.-]\\s*)?)([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})",
            message = PHONE_NUMBER_FORMAT_MESSAGE)
    private String secondaryPhone;

}
