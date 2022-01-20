package com.asimkilic.loan.application.converter.customer;

import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.entity.PhoneBook;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    PhoneBookMapper INSTANCE_PHONEBOOK_MAPPER = Mappers.getMapper(PhoneBookMapper.class);

    Customer convertToCustomer(CustomerSaveRequestDto customerSaveRequestDto);

    @AfterMapping
    default void setPhoneBooksFromSaveDto(@MappingTarget Customer customer, CustomerSaveRequestDto customerSaveRequestDto) {
       /* customerSaveRequestDto.getPhones().stream().map(INSTANCE_PHONEBOOK_MAPPER::convertToPhoneBook).forEach(
                phone -> customer.getPhones().add(phone)
        );*/
        customer.setPhones(new HashSet<>());
    }

    CustomerDto convertToCustomerDto(Customer customer);

    @AfterMapping
    default void setPhonesFromPhoneBook(@MappingTarget CustomerDto customerDto, Customer customer) {
        customerDto.setPhones(
                customer.getPhones()
                        .stream()
                        .map(INSTANCE_PHONEBOOK_MAPPER::convertToPhoneBookPartialDto)
                        .collect(Collectors.toSet()));
    }
}
