package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.converter.customer.CustomerConverter;
import com.asimkilic.loan.application.dto.credit.CreditResultRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDeleteRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.exception.customer.*;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
import com.asimkilic.loan.application.gen.service.turkishrepublicidno.BaseTurkishRepublicIdNoVerificationService;
import com.asimkilic.loan.application.service.entityservice.customer.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.asimkilic.loan.application.converter.customer.CustomerMapper.INSTANCE;
import static com.asimkilic.loan.application.gen.message.InfoMessage.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerEntityService customerEntityService;
    private final BaseTurkishRepublicIdNoVerificationService trIdNoVerificationService;
    private final CreditService creditService;


    public List<CustomerDto> findAllUsers() {
        return customerEntityService
                .findAllActiveCustomers()
                .stream()
                .map(INSTANCE::convertToCustomerDto)
                .collect(Collectors.toList());

    }

    @Transactional(propagation=Propagation.SUPPORTS)
    public BaseCreditResponse applyCreditByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        Customer customer = customerEntityService
                .findCustomerByTurkishRepublicIdNo(turkishRepublicIdNo)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
        CustomerDto customerDto = INSTANCE.convertToCustomerDto(customer);

        BaseCreditResponse baseCreditResponse = creditService.applyCredit(customerDto);
        return baseCreditResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseCreditResponse saveNewCustomer(CustomerSaveRequestDto customerSaveRequestDto) {
        Customer newCustomer = INSTANCE.convertToCustomer(customerSaveRequestDto);
        validateCustomerTurkishRepublicIdNo(newCustomer);


        Optional<Customer> deletedCustomer = findDeletedCustomerIfExists(newCustomer.getTurkishRepublicIdNo());
        if (deletedCustomer.isPresent()) {
            CustomerUpdateRequestDto updateRequestDto = CustomerUpdateRequestDto.builder().id(deletedCustomer.get().getId()).email(deletedCustomer.get().getEmail()).primaryPhone(deletedCustomer.get().getPrimaryPhone()).build();
            validateUpdateCustomerEmailCredentialNotInUse(updateRequestDto);
            validateUpdateCustomerPrimaryPhoneCredentialNotInUse(updateRequestDto);
            newCustomer = CustomerConverter.convertDeletedCustomerToNewCustomer(newCustomer, deletedCustomer.get());
            newCustomer.setUpdateTime(getLocalDateTimeNow());
        } else {

            newCustomer.setStatus(EnumCustomerStatus.ACTIVE);
            newCustomer.setCreationTime(getLocalDateTimeNow());
            checkTurkishRepublicIdNoIsValidForNewCustomer(newCustomer.getTurkishRepublicIdNo());
            checkEmailAddressIsValidForNewCustomer(newCustomer.getEmail());
            checkPrimaryPhoneIsValidForNewCustomer(newCustomer.getPrimaryPhone());
        }

        newCustomer = customerEntityService.save(newCustomer);
        CustomerDto customerDto = INSTANCE.convertToCustomerDto(newCustomer);

        BaseCreditResponse baseCreditResponse = creditService.applyCredit(customerDto);

        return baseCreditResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDto updateCustomer(CustomerUpdateRequestDto customerUpdateRequestDto) {

        Customer persistCustomerById = customerEntityService
                .findById(customerUpdateRequestDto.getId()).orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
        validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto);
        validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customerUpdateRequestDto);

        CustomerConverter.convertUpdatedCustomerToExistCustomer(customerUpdateRequestDto, persistCustomerById);

        persistCustomerById.setUpdateTime(getLocalDateTimeNow());
        persistCustomerById = customerEntityService.save(persistCustomerById);
        return INSTANCE.convertToCustomerDto(persistCustomerById);
    }

    public CustomerDto findCustomerById(String customerId) {
        Customer customer = customerEntityService
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));

        return INSTANCE.convertToCustomerDto(customer);
    }

    public CustomerDto findCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        Customer customer = customerEntityService
                .findCustomerByTurkishRepublicIdNo(turkishRepublicIdNo)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));

        return INSTANCE.convertToCustomerDto(customer);
    }

    public void deleteCustomer(CustomerDeleteRequestDto deleteRequestDto) {
        Customer customer = customerEntityService.findCustomerByTurkishRepublicIdNo(deleteRequestDto.getTurkishRepublicIdNo())
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));

        setDeletingCustomerStatusFalseAndUpdateTimeAndSave(customer);
    }

    public BaseCreditResponse findCreditResult(CreditResultRequestDto creditResultRequestDto) {
        CustomerDto customerDto = findCustomerByTurkishRepublicIdNo(creditResultRequestDto.getTurkishRepublicIdNo());
        String dateOfBirthOfCustomerDtoDate = new SimpleDateFormat("yyyy-MM-dd").format(customerDto.getDateOfBirth());
        String dateOfBirthOfCreditResultDtoDate = new SimpleDateFormat("yyyy-MM-dd").format(creditResultRequestDto.getDateOfBirth());
        if (!dateOfBirthOfCustomerDtoDate.equals(dateOfBirthOfCreditResultDtoDate)) {
            throw new IllegalCustomerUpdateArgumentException(CUSTOMER_ARGUMENTS_INVALID);
        }
        return creditService.findCreditResult(creditResultRequestDto);
    }

    protected void validateUpdateCustomerEmailCredentialNotInUse(final CustomerUpdateRequestDto customerRequestDto) {
        if (customerRequestDto.getEmail() != null) {
            boolean emailInUse = customerEntityService.validateUpdateCustomerEmailCredentialNotInUse(customerRequestDto.getId(), customerRequestDto.getEmail());
            if (emailInUse) {
                throw new EmailIsAlreadySavedException(EMAIL_IS_ALREADY_SAVED);
            }
        }
    }

    protected void validateUpdateCustomerPrimaryPhoneCredentialNotInUse(final CustomerUpdateRequestDto customerRequestDto) {
        if (customerRequestDto.getPrimaryPhone() != null) {
            boolean phoneInUse = customerEntityService.validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customerRequestDto.getId(), customerRequestDto.getPrimaryPhone());
            if (phoneInUse) {
                throw new PhoneIsAlreadySavedException(PHONE_NUMBER_IS_ALREADY_SAVED);
            }
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

    protected void validateCustomerTurkishRepublicIdNo(final Customer customer) {
        boolean verified = trIdNoVerificationService
                .verifyTurkishRepublicIdNo(CustomerConverter.convertToVerifyCustomerTurkishRepublicIdNoRequestDto(customer));

        if (!verified) {
            throw new IllegalCustomerUpdateArgumentException(CUSTOMER_CREDENTIALS_ARE_NOT_VALID);
        }
    }

    protected void checkTurkishRepublicIdNoIsValidForNewCustomer(String turkishRepublicIdNo) {
        boolean resultTurkishIdNoIsExist = existsCustomerByTurkishRepublicIdNo(turkishRepublicIdNo);
        if (resultTurkishIdNoIsExist) {
            throw new TurkishRepublicIdNoIsAlreadySavedException(TURKISH_REPUBLIC_ID_NO_IS_ALREADY_TAKEN);
        }
    }

    protected void checkEmailAddressIsValidForNewCustomer(String email) {
        boolean resultEmailIsExist = existsCustomerByEmail(email);
        if (resultEmailIsExist) {
            throw new EmailIsAlreadySavedException(EMAIL_IS_ALREADY_SAVED);
        }
    }

    protected void checkPrimaryPhoneIsValidForNewCustomer(String primaryPhone) {
        boolean resultPrimaryPhoneIsExist = existsCustomerByPrimaryPhone(primaryPhone);
        if (resultPrimaryPhoneIsExist) {
            throw new PhoneIsAlreadySavedException(PHONE_NUMBER_IS_ALREADY_SAVED);
        }

    }

    private void setDeletingCustomerStatusFalseAndUpdateTimeAndSave(Customer customer) {
        customer.setStatus(EnumCustomerStatus.DELETED);
        customer.setUpdateTime(getLocalDateTimeNow());
        customerEntityService.save(customer);
    }

    private Optional<Customer> findDeletedCustomerIfExists(String turkishRepublicIdNo) {
        return customerEntityService.findDeletedCustomerIfExist(turkishRepublicIdNo);
    }

    private LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now();
        // Instant instant = clock.instant();
        // return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
    }

}
