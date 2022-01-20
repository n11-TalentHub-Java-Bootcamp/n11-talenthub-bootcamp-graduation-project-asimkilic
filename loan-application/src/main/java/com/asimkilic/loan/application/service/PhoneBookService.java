package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.converter.customer.PhoneBookMapper;
import com.asimkilic.loan.application.dto.phonebook.PhoneBookSaveRequestForNewCustomerDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.entity.PhoneBook;
import com.asimkilic.loan.application.exception.customer.PhoneIsAlreadySavedException;
import com.asimkilic.loan.application.service.entityservice.customer.PhoneBookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.asimkilic.loan.application.generic.message.InfoMessage.PHONE_NUMBER_IS_ALREADY_SAVED;

@Service
@RequiredArgsConstructor
public class PhoneBookService {
    private final PhoneBookEntityService phoneBookEntityService;

    protected boolean existsPhoneBookByPhone(String phone) {
        return phoneBookEntityService.existsPhoneBookByPhone(phone);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    protected List<PhoneBook> savePhoneList(Set<PhoneBookSaveRequestForNewCustomerDto> phoneBookListDto, Customer customer) {
        List<PhoneBook> phoneBookList = phoneBookListDto
                .stream()
                .map(PhoneBookMapper.INSTANCE::convertToPhoneBook)
                .collect(Collectors.toList());
        checkPhonesAreValidForCreation(phoneBookList);
        phoneBookList.forEach(phone -> phone.setCustomer(customer));
        return phoneBookEntityService.saveAll(phoneBookList);
    }

    private void checkPhonesAreValidForCreation(List<PhoneBook> phoneBookList) {
        phoneBookList.forEach(phone -> {
            if (existsPhoneBookByPhone(phone.getPhone())) {
                throw new PhoneIsAlreadySavedException(PHONE_NUMBER_IS_ALREADY_SAVED);
            }
        });
    }
}
