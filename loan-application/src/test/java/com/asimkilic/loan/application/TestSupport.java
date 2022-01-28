package com.asimkilic.loan.application;

import com.asimkilic.loan.application.dto.credit.CreditCalculationRequestDto;
import com.asimkilic.loan.application.dto.credit.CreditResultRequestDto;
import com.asimkilic.loan.application.dto.customer.*;
import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class TestSupport {


    public Customer getFirstCustomer() {
        return getAllCustomer().get(0);
    }

    public CustomerDto getFirstCustomerDto() {
        return convertToCustomerDto().get(0);
    }

    public CustomerSaveRequestDto getFirstCustomerSaveRequestDto() {
        CustomerSaveRequestDto saveRequestDto = CustomerSaveRequestDto.builder()
                .turkishRepublicIdNo("10020030040")
                .firstName("customer-1")
                .lastName("customer-1")
                .dateOfBirth(new Date(1991, Calendar.JANUARY, 1))
                .email("customer-1@customer.com")
                .monthlySalary(BigDecimal.valueOf(1500))
                .amountOfGuarantee(BigDecimal.valueOf(2000))
                .primaryPhone("5329998877")
                .secondaryPhone("5339998877")
                .build();
        return saveRequestDto;

    }

    public CustomerDeleteRequestDto getFirstCustomerDeleteRequestDto() {
        CustomerDeleteRequestDto deleteRequestDto = new CustomerDeleteRequestDto();
        deleteRequestDto.setTurkishRepublicIdNo("10020030040");
        return deleteRequestDto;

    }

    public CustomerUpdateRequestDto getFirstCustomerUpdateRequestDto() {
        CustomerUpdateRequestDto updateRequestDto = CustomerUpdateRequestDto.builder().id("UUID-1")
                .email("customer-1-updated@customer.com")
                .monthlySalary(BigDecimal.valueOf(2000))
                .amountOfGuarantee(BigDecimal.valueOf(2500))
                .primaryPhone("5329998877")
                .secondaryPhone("5339998877")
                .build();
        return updateRequestDto;
    }

    public CreditResultRequestDto getFirstCustomerCreditRequestDto() {
        CreditResultRequestDto requestDto = CreditResultRequestDto.builder()
                .turkishRepublicIdNo("10020030040")
                .dateOfBirth(new Date(1991, Calendar.JANUARY, 1))
                .build();
        return requestDto;
    }

    public CreditResultRequestDto getSecondCustomerCreditRequestDto() {
        CreditResultRequestDto requestDto = CreditResultRequestDto.builder()
                .turkishRepublicIdNo("20020030040")
                .dateOfBirth(new Date(1992, 2, 2))
                .build();
        return requestDto;
    }

    public CreditResultRequestDto getFirstCustomerCreditResultDto() {
        CreditResultRequestDto creditResultRequestDto = CreditResultRequestDto.builder()
                .turkishRepublicIdNo("10020030040")
                .dateOfBirth(new Date(1991, Calendar.JANUARY, 1))
                .build();
        return creditResultRequestDto;

    }

    public Credit getFirstCustomerApprovedCredit() {
        Credit credit = Credit.builder()
                .id("Credit-1")
                .customer(getFirstCustomer())
                .creditConstraint(getFirstCustomerCreditConstraint())
                .creditLimit(BigDecimal.valueOf(10200))
                .creationTime(getLocalDateTime())
                .salaryAtTheTimeOfApplication(getFirstCustomer().getMonthlySalary())
                .amountOfGuaranteeAtTheTimeOfApplication((getFirstCustomer().getAmountOfGuarantee()))
                .creditScoreAtTheTimeOfApplication(BigDecimal.valueOf(1500))
                .creditStatus(EnumCreditStatus.APPROVED)
                .build();
        return credit;
    }
    public Credit getFirstCustomerDeniedCredit() {
        Credit credit = Credit.builder()
                .id("Credit-1")
                .customer(getFirstCustomer())
                .creditConstraint(null)
                .creditLimit(null)
                .creationTime(getLocalDateTime())
                .salaryAtTheTimeOfApplication(getFirstCustomer().getMonthlySalary())
                .amountOfGuaranteeAtTheTimeOfApplication((getFirstCustomer().getAmountOfGuarantee()))
                .creditScoreAtTheTimeOfApplication(BigDecimal.valueOf(1500))
                .creditStatus(EnumCreditStatus.DENIED)
                .build();
        return credit;
    }

    public CreditConstraint getFirstCustomerCreditConstraint() {
        CreditConstraint creditConstraint = CreditConstraint.builder()
                .id("credit-constraint-1")
                .creditLimit(BigDecimal.valueOf(10000))
                .percentageOfGuarantee(BigDecimal.valueOf(10))
                .creditLimitMultiplierCoefficient(BigDecimal.ZERO)
                .creditScoreLowerLimit(BigDecimal.valueOf(500))
                .creditScoreLowerLimit(BigDecimal.valueOf(1000))
                .salaryLowerLimit(BigDecimal.valueOf(0))
                .salaryUpperLimit(BigDecimal.valueOf(5000))
                .build();
        return creditConstraint;
    }


    public VerifyCustomerTurkishRepublicIdNoRequestDto getFirstCustomerVerifyTurkishRepublicIdNoRequestDto() {
        Customer customer = getFirstCustomer();
        VerifyCustomerTurkishRepublicIdNoRequestDto dto = VerifyCustomerTurkishRepublicIdNoRequestDto.builder()
                .firstName(customer.getFirstName().toUpperCase(Locale.ROOT))
                .lastName(customer.getLastName().toUpperCase(Locale.ROOT))
                .yearOfBirth(String.valueOf(customer.getDateOfBirth().getYear() + 1900))
                .turkishRepublicIdentityNo(customer.getTurkishRepublicIdNo()).build();

        return dto;
    }

    public List<Customer> getAllCustomer() {
        List<Customer> allCustomers = new ArrayList<>();
        Customer firstCustomer = Customer.builder()
                .id("UUID-1")
                .turkishRepublicIdNo("10020030040")
                .firstName("customer-1")
                .lastName("customer-1")
                .dateOfBirth(new Date(1991, Calendar.JANUARY, 1))
                .email("customer-1@customer.com")
                .monthlySalary(BigDecimal.valueOf(1500))
                .amountOfGuarantee(BigDecimal.valueOf(2000))
                .primaryPhone("5329998877")
                .secondaryPhone("5339998877")
                .status(EnumCustomerStatus.ACTIVE)
                .creationTime(getLocalDateTime()).build();
        allCustomers.add(firstCustomer);
        Customer secondCustomer = Customer.builder()
                .id("UUID-2")
                .turkishRepublicIdNo("20020030040")
                .firstName("customer-2")
                .lastName("customer-2")
                .dateOfBirth(new Date(1992, Calendar.FEBRUARY, 2))
                .email("customer-2@customer.com")
                .monthlySalary(BigDecimal.valueOf(2500))
                .amountOfGuarantee(BigDecimal.valueOf(3000))
                .primaryPhone("5339998877")
                .secondaryPhone("5349998877")
                .status(EnumCustomerStatus.ACTIVE)
                .creationTime(getLocalDateTime()).build();
        allCustomers.add(secondCustomer);
        Customer thirdCustomer = Customer.builder()
                .id("UUID-3")
                .turkishRepublicIdNo("30020030040")
                .firstName("customer-3")
                .lastName("customer-3")
                .dateOfBirth(new Date(1993, Calendar.MARCH, 3))
                .email("customer-3@customer.com")
                .monthlySalary(BigDecimal.valueOf(3500))
                .amountOfGuarantee(BigDecimal.valueOf(4000))
                .primaryPhone("5349998877")
                .secondaryPhone("5359998877")
                .status(EnumCustomerStatus.ACTIVE)
                .creationTime(getLocalDateTime()).build();
        allCustomers.add(thirdCustomer);
        return allCustomers;
    }

    public List<CustomerDto> convertToCustomerDto() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        CustomerDto firstCustomerDto = CustomerDto.builder()
                .id("UUID-1")
                .turkishRepublicIdNo("10020030040")
                .firstName("customer-1")
                .lastName("customer-1")
                .dateOfBirth(new Date(1991, Calendar.JANUARY, 1))
                .email("customer-1@customer.com")
                .monthlySalary(BigDecimal.valueOf(1500))
                .amountOfGuarantee(BigDecimal.valueOf(2000))
                .primaryPhone("5329998877")
                .secondaryPhone("5339998877")
                .creationTime(getLocalDateTime()).build();
        customerDtoList.add(firstCustomerDto);
        CustomerDto secondCustomerDto = CustomerDto.builder()
                .id("UUID-2")
                .turkishRepublicIdNo("20020030040")
                .firstName("customer-2")
                .lastName("customer-2")
                .dateOfBirth(new Date(1992, Calendar.FEBRUARY, 2))
                .email("customer-2@customer.com")
                .monthlySalary(BigDecimal.valueOf(2500))
                .amountOfGuarantee(BigDecimal.valueOf(3000))
                .primaryPhone("5339998877")
                .secondaryPhone("5349998877")
                .creationTime(getLocalDateTime()).build();
        customerDtoList.add(secondCustomerDto);
        CustomerDto thirdCustomerDto = CustomerDto.builder()
                .id("UUID-3")
                .turkishRepublicIdNo("30020030040")
                .firstName("customer-3")
                .lastName("customer-3")
                .dateOfBirth(new Date(1993, Calendar.MARCH, 3))
                .email("customer-3@customer.com")
                .monthlySalary(BigDecimal.valueOf(3500))
                .amountOfGuarantee(BigDecimal.valueOf(4000))
                .primaryPhone("5349998877")
                .secondaryPhone("5359998877")
                .creationTime(getLocalDateTime()).build();
        customerDtoList.add(thirdCustomerDto);
        return customerDtoList;
    }

    public Instant getCurrentInstant() {
        String instantExpected = "2022-01-27T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public BaseCreditResponse getFirstCustomerApprovedCreditResponse() {
        return () -> EnumCreditStatus.APPROVED;
    }

    public BaseCreditResponse getFirstCustomerDeniedCreditResponse() {
        return () -> EnumCreditStatus.DENIED;
    }

    public CreditCalculationRequestDto getFirstCustomerCreditCalculationRequestDto() {
        CreditCalculationRequestDto creditCalculationRequestDto = CreditCalculationRequestDto
                .builder()
                .clientSalary(BigDecimal.valueOf(5000))
                .clientAmountOfGuarantee(BigDecimal.valueOf(1000))
                .creditLimit(BigDecimal.valueOf(10000))
                .percentageOfGuarantee(BigDecimal.valueOf(10))
                .creditLimitMultiplierCoefficient(BigDecimal.ZERO)
                .build();
        return creditCalculationRequestDto;
    }
}
