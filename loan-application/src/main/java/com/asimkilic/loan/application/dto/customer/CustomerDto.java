package com.asimkilic.loan.application.dto.customer;

import com.asimkilic.loan.application.dto.phonebook.PhoneBookPartialDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Schema(description = "It is used to view the Customer")
public class CustomerDto {

    private String id;

    private String turkishRepublicIdNo;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String email;

    private BigDecimal monthlySalary;

    private BigDecimal amountOfGuarantee;

    private LocalDateTime creationTime;

    private Set<PhoneBookPartialDto> phones;


}
