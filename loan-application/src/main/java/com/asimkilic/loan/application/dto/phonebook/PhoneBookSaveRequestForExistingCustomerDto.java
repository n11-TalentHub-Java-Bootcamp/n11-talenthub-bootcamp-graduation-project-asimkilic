package com.asimkilic.loan.application.dto.phonebook;

import com.asimkilic.loan.application.generic.message.InfoMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.asimkilic.loan.application.generic.message.InfoMessage.*;

@Data
@Schema(description = "It is used to save new phone to existing customer")
public class PhoneBookSaveRequestForExistingCustomerDto {

    @NotNull(message = PHONE_NUMBER_CANNOT_NULL)
    @Pattern(regexp = "(?:(?:(\\s*\\(?([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\)?\\s*(?:[.-]\\s*)?)([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})",
            message = PHONE_NUMBER_FORMAT_MESSAGE)
    private String phone;

    @NotNull(message = CUSTOMER_ID_CANNOT_NULL)
    private String customerId;
}
