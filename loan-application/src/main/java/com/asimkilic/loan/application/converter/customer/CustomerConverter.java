package com.asimkilic.loan.application.converter.customer;

import com.asimkilic.loan.application.dto.customer.KpsPublicVerifyCustomerRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;

import java.math.BigDecimal;
import java.util.Locale;

public class CustomerConverter {

    public static KpsPublicVerifyCustomerRequestDto convertToKpsPublicVerifyRequestDto(Customer customer) {

        KpsPublicVerifyCustomerRequestDto dto = KpsPublicVerifyCustomerRequestDto.builder()
                .firstName(customer.getFirstName().toUpperCase(Locale.ROOT))
                .lastName(customer.getLastName().toUpperCase(Locale.ROOT))
                .yearOfBirth(String.valueOf(customer.getDateOfBirth().getYear()+1900))
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
}
