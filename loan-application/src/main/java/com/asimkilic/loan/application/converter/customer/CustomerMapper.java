package com.asimkilic.loan.application.converter.customer;

import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.entity.Customer;

import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    Customer convertToCustomer(CustomerUpdateRequestDto customerUpdateRequestDto);
    Customer convertToCustomer(CustomerSaveRequestDto customerSaveRequestDto);

    CustomerDto convertToCustomerDto(Customer customer);



}
