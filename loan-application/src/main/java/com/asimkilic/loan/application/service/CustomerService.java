package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.converter.customer.PhoneBookMapper;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.dto.phonebook.PhoneBookPartialDto;
import com.asimkilic.loan.application.dto.phonebook.PhoneBookSaveRequestForNewCustomerDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.entity.PhoneBook;
import com.asimkilic.loan.application.exception.customer.EmailIsAlreadySavedException;
import com.asimkilic.loan.application.exception.customer.TurkishRepublicIdNoIsAlreadySavedException;
import com.asimkilic.loan.application.service.entityservice.customer.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.asimkilic.loan.application.converter.customer.CustomerMapper.INSTANCE;
import static com.asimkilic.loan.application.converter.customer.CustomerMapper.INSTANCE_PHONEBOOK_MAPPER;
import static com.asimkilic.loan.application.generic.message.InfoMessage.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerEntityService customerEntityService;
    private final PhoneBookService phoneBookService;
    private final Clock clock;

    public List<CustomerDto> findAllUsers() {
        return customerEntityService
                .findAll()
                .stream()
                .map(INSTANCE::convertToCustomerDto)
                .collect(Collectors.toList());

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDto saveNewCustomer(CustomerSaveRequestDto customerSaveRequestDto) {
        Customer newCustomer = INSTANCE.convertToCustomer(customerSaveRequestDto);
        checkCustomerIsValidForCreation(newCustomer);
        newCustomer.setCreationTime(getLocalDateTimeNow());
        newCustomer = customerEntityService.save(newCustomer);

        List<PhoneBook> phoneBooks = phoneBookService.savePhoneList(customerSaveRequestDto.getPhones(), newCustomer);
        Set<PhoneBookPartialDto> phoneBookPartialDtoSet = phoneBooks.stream().map(INSTANCE_PHONEBOOK_MAPPER::convertToPhoneBookPartialDto).collect(Collectors.toSet());

        CustomerDto customerDto = INSTANCE.convertToCustomerDto(newCustomer);
        customerDto.setPhones(phoneBookPartialDtoSet);

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
