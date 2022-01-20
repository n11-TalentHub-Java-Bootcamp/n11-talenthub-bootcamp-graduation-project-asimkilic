package com.asimkilic.loan.application.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

    private String primaryPhone;

    private String secondaryPhone;

    private LocalDateTime updateTime;

    private LocalDateTime creationTime;


}
