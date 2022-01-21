package com.asimkilic.loan.application.converter.customer;

import com.asimkilic.loan.application.dto.customer.KpsPublicVerifyCustomerRequestDto;
import com.asimkilic.loan.application.entity.Customer;

import java.util.Locale;

public class CustomerConverter {

    public static KpsPublicVerifyCustomerRequestDto convertToKpsPublicVerifyRequestDto(Customer customer) {

        KpsPublicVerifyCustomerRequestDto dto = KpsPublicVerifyCustomerRequestDto.builder()
                .firstName(customer.getFirstName().toUpperCase(Locale.ROOT))
                .lastName(customer.getLastName().toUpperCase(Locale.ROOT))
                .yearOfBirth(String.valueOf(customer.getDateOfBirth().getYear()))
                .turkishRepublicIdentityNo(customer.getTurkishRepublicIdNo()).build();

        return dto;
    }
}
