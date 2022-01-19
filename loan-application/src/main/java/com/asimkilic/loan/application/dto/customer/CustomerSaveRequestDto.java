package com.asimkilic.loan.application.dto.customer;

import com.asimkilic.loan.application.dto.phonebook.PhoneBookSaveRequestForExistingCustomerDto;
import com.asimkilic.loan.application.dto.phonebook.PhoneBookSaveRequestForNewCustomerDto;
import com.asimkilic.loan.application.generic.message.InfoMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import static com.asimkilic.loan.application.generic.message.InfoMessage.*;

@Data
@Schema(description = "It is used to save new customer")
public class CustomerSaveRequestDto {


    @NotNull
    @Size(max = 100)
    private String firstName;

    @NotNull
    @Size(max = 100)
    private String lastName;

    @NotNull(message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Size(min = 11,max = 11,message = CUSTOMER_TR_ID_NO_DESCRIPTION)
    @Schema(description = CUSTOMER_TR_ID_NO_DESCRIPTION)
    private String turkishRepublicIdNo;

    @Past
    private Date dateOfBirth;

    @NotNull
    @Min(value = 0)
    private BigDecimal monthlySalary;


    private BigDecimal amountOfGuarantee;

    @NotNull
    private Set<PhoneBookSaveRequestForNewCustomerDto> phones;
}
