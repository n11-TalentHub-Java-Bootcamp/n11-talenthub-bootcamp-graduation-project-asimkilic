package com.asimkilic.loan.application.converter.customer;

import com.asimkilic.loan.application.dto.phonebook.PhoneBookPartialDto;
import com.asimkilic.loan.application.dto.phonebook.PhoneBookSaveRequestForNewCustomerDto;
import com.asimkilic.loan.application.entity.PhoneBook;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PhoneBookMapper {
    PhoneBookMapper INSTANCE = Mappers.getMapper(PhoneBookMapper.class);

    PhoneBook convertToPhoneBook(PhoneBookSaveRequestForNewCustomerDto phoneDto);

    PhoneBookPartialDto convertToPhoneBookPartialDto(PhoneBook phoneBook);
}
