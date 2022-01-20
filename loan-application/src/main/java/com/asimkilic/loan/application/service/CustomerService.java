package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.converter.customer.CustomerMapper;
import com.asimkilic.loan.application.converter.customer.PhoneBookMapper;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.dto.phonebook.PhoneBookSaveRequestForNewCustomerDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.entity.PhoneBook;
import com.asimkilic.loan.application.exception.customer.EmailIsAlreadySavedException;
import com.asimkilic.loan.application.exception.customer.PhoneIsAlreadySavedException;
import com.asimkilic.loan.application.exception.customer.TurkishRepublicIdNoIsAlreadySavedException;
import com.asimkilic.loan.application.generic.message.InfoMessage;
import com.asimkilic.loan.application.service.entityservice.customer.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.asimkilic.loan.application.converter.customer.CustomerMapper.INSTANCE;
import static com.asimkilic.loan.application.generic.message.InfoMessage.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerEntityService customerEntityService;
    private final PhoneBookService phoneBookService;
    private final Clock clock;

    public List<CustomerDto> findAllUsers() {
        return null;
    }

    public CustomerDto saveNewCustomer(CustomerSaveRequestDto customerSaveRequestDto) {
         Set<PhoneBookSaveRequestForNewCustomerDto> phones = customerSaveRequestDto.getPhones();
        Customer newCustomer = INSTANCE.convertToCustomer(customerSaveRequestDto);
        checkCustomerIsValidForCreation(newCustomer);
        newCustomer.setCreationTime(getLocalDateTimeNow());
        newCustomer = customerEntityService.save(newCustomer);

        Customer finalNewCustomer = newCustomer;
        List<PhoneBook> phoneList = phones.stream().map(PhoneBookMapper.INSTANCE::convertToPhoneBook).collect(Collectors.toList());
        phoneList.forEach(phone->phone.setCustomer(finalNewCustomer));
        List<PhoneBook> phoneBooks = phoneBookService.savePhoneList(phoneList);
        newCustomer.setPhones(phoneBooks.stream().collect(Collectors.toSet()));
        CustomerDto customerDto = INSTANCE.convertToCustomerDto(newCustomer);
        return customerDto;
    }

    protected boolean existsCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return customerEntityService.existsCustomerByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    protected boolean existsCustomerByEmail(String email) {
        return customerEntityService.existsCustomerByEmail(email);
    }

    protected boolean existsCustomerById(String id) {
        return customerEntityService.existsCustomerById(id);
    }

    protected boolean existsPhoneBookByPhone(String phone) {
        return phoneBookService.existsPhoneBookByPhone(phone);
    }

    private void checkCustomerIsValidForCreation(Customer customer) {
        boolean resultTurkishIdNoIsExist = existsCustomerByTurkishRepublicIdNo(customer.getTurkishRepublicIdNo());
        if (resultTurkishIdNoIsExist) {
            throw new TurkishRepublicIdNoIsAlreadySavedException(TURKISH_REPUBLIC_ID_NO_IS_ALREADY_TAKEN);
        }

        for (PhoneBook phone : customer.getPhones()) {
            if (existsPhoneBookByPhone(phone.getPhone())) {
                throw new PhoneIsAlreadySavedException(PHONE_NUMBER_IS_ALREADY_SAVED);
            }
        }

        boolean resultEmailIsExist = existsCustomerByEmail(customer.getEmail());
        if (resultEmailIsExist) {
            throw new EmailIsAlreadySavedException(EMAIL_IS_ALREADY_SAVED);
        }
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
    }
}
