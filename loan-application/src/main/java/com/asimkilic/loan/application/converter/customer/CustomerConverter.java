package com.asimkilic.loan.application.converter.customer;

import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.dto.customer.VerifyCustomerTurkishRepublicIdNoRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;

import java.math.BigDecimal;
import java.util.Locale;

public class CustomerConverter {

    public static VerifyCustomerTurkishRepublicIdNoRequestDto convertToVerifyCustomerTurkishRepublicIdNoRequestDto(Customer customer) {

        VerifyCustomerTurkishRepublicIdNoRequestDto dto = VerifyCustomerTurkishRepublicIdNoRequestDto.builder()
                .firstName(customer.getFirstName().toUpperCase(Locale.ROOT))
                .lastName(customer.getLastName().toUpperCase(Locale.ROOT))
                .yearOfBirth(String.valueOf(customer.getDateOfBirth().getYear() + 1900))
                .turkishRepublicIdentityNo(customer.getTurkishRepublicIdNo()).build();

        return dto;
    }

    public static Customer convertDeletedCustomerToNewCustomer(Customer source, Customer target) {

        target.setStatus(EnumCustomerStatus.ACTIVE);
        target.setEmail(source.getEmail());
        target.setPrimaryPhone(source.getPrimaryPhone());
        target.setSecondaryPhone(source.getSecondaryPhone());
        target.setMonthlySalary(source.getMonthlySalary());
        if (source.getAmountOfGuarantee().compareTo(BigDecimal.ZERO) > 0) {
            target.setAmountOfGuarantee(source.getAmountOfGuarantee());
        }
        return target;
    }

    public static void convertUpdatedCustomerToExistCustomer(CustomerUpdateRequestDto updatedCustomer, Customer persistCustomer) {
        if (updatedCustomer.getPrimaryPhone() != null) {
            persistCustomer.setPrimaryPhone(updatedCustomer.getPrimaryPhone());
        }
        if (updatedCustomer.getEmail() != null) {
            persistCustomer.setEmail(updatedCustomer.getEmail());
        }
        if (updatedCustomer.getMonthlySalary() != null) {
            persistCustomer.setMonthlySalary(updatedCustomer.getMonthlySalary());
        }
        if (updatedCustomer.getAmountOfGuarantee() != null) {
            persistCustomer.setAmountOfGuarantee(updatedCustomer.getAmountOfGuarantee());
        }
        if (updatedCustomer.getSecondaryPhone() != null) {
            persistCustomer.setSecondaryPhone(updatedCustomer.getSecondaryPhone());
        }

    }
}
