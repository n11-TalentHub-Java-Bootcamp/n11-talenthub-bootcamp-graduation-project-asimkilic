package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.converter.customer.CustomerConverter;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.exception.customer.*;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
import com.asimkilic.loan.application.service.entityservice.customer.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.gov.nvi.tckimlik.WS.KpsPublicSoapService;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.asimkilic.loan.application.converter.customer.CustomerMapper.INSTANCE;
import static com.asimkilic.loan.application.gen.message.InfoMessage.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerEntityService customerEntityService;
    private final KpsPublicSoapService kpsPublicSoapService;
    private final Clock clock;

    public List<CustomerDto> findAllUsers() {
        return customerEntityService
                .findAll()
                .stream()
                .filter(x -> x.getStatus() == EnumCustomerStatus.ACTIVE)
                .map(INSTANCE::convertToCustomerDto)
                .collect(Collectors.toList());

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDto saveNewCustomer(CustomerSaveRequestDto customerSaveRequestDto) {
        Customer newCustomer = INSTANCE.convertToCustomer(customerSaveRequestDto);

        boolean verified = kpsPublicSoapService.verifyTurkishRepublicIdNo(CustomerConverter.convertToKpsPublicVerifyRequestDto(newCustomer));
        if (!verified) {
            throw new IllegalCustomerUpdateArgumentException(CUSTOMER_CREDENDITALS_ARE_NOT_VALID);
        }
        // ACTIVE kayıtlar için de var mı? varsa return exception zaten var
        // DELETED içinde var mı varsa güncelle ve ACTIVE YAP
        // YENI KAYIT OLARAK EKLE
        checkCustomerIsValidForCreation(newCustomer);

        newCustomer.setStatus(EnumCustomerStatus.ACTIVE);
        newCustomer.setCreationTime(getLocalDateTimeNow());
        newCustomer = customerEntityService.save(newCustomer);

        return INSTANCE.convertToCustomerDto(newCustomer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDto updateCustomer(CustomerUpdateRequestDto customerUpdateRequestDto) {
        Customer customer = INSTANCE.convertToCustomer(customerUpdateRequestDto);
        validateUpdateCustomerCredentialsNotInUse(customer);
        customer.setUpdateTime(getLocalDateTimeNow());
        customer = customerEntityService.save(customer);
        return INSTANCE.convertToCustomerDto(customer);
    }

    public void deleteCustomerByCustomerId(String customerId) {
        Customer customer = customerEntityService
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));

        setDeletingCustomerStatusFalseAndUpdateTimeAndSave(customer);
    }

    public void deleteCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        Customer customer = customerEntityService.findCustomerByTurkishRepublicIdNo(turkishRepublicIdNo)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));

        setDeletingCustomerStatusFalseAndUpdateTimeAndSave(customer);
    }

    protected void validateUpdateCustomerCredentialsNotInUse(final Customer customer) {
        boolean inUse = customerEntityService
                .validateUpdateCustomerCredentialsNotInUse(customer.getId(), customer.getTurkishRepublicIdNo(), customer.getEmail(), customer.getPrimaryPhone());
        if (inUse) {
            throw new IllegalCustomerUpdateArgumentException(CUSTOMER_ARGUMENTS_INVALID);
        }
    }

    protected boolean existsCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return customerEntityService.existsCustomerByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    protected boolean existsCustomerByEmail(String email) {
        return customerEntityService.existsCustomerByEmail(email);
    }

    protected boolean existsCustomerById(String id) {
        return customerEntityService.existsActiveCustomerById(id);
    }


    protected boolean existsCustomerByPrimaryPhone(String primaryPhone) {
        return customerEntityService.existsCustomerByPrimaryPhone(primaryPhone);
    }

    private void setDeletingCustomerStatusFalseAndUpdateTimeAndSave(Customer customer) {
        customer.setStatus(EnumCustomerStatus.DELETED);
        customer.setUpdateTime(getLocalDateTimeNow());
        customerEntityService.save(customer);
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

        boolean resultPrimaryPhoneIsExist = existsCustomerByPrimaryPhone(customer.getPrimaryPhone());
        if (resultPrimaryPhoneIsExist) {
            throw new PhoneIsAlreadySavedException(PHONE_NUMBER_IS_ALREADY_SAVED);
        }

    }


    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
    }


}
