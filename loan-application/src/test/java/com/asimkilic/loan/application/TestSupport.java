package com.asimkilic.loan.application;

import com.asimkilic.loan.application.dto.customer.CustomerDeleteRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestSupport {


    public Customer getFirstCustomer() {
        return getAllCustomer().get(0);
    }

    public CustomerDto getFirstCustomerDto() {
        return convertToCustomerDto().get(0);
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

}