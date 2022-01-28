package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.TestSupport;
import com.asimkilic.loan.application.dto.credit.ApprovedCreditResponse;
import com.asimkilic.loan.application.dto.credit.CreditResultRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDeleteRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.dto.customer.VerifyCustomerTurkishRepublicIdNoRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.exception.customer.*;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
import com.asimkilic.loan.application.gen.service.turkishrepublicidno.BaseTurkishRepublicIdNoVerificationService;
import com.asimkilic.loan.application.service.entityservice.customer.CustomerEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


import static com.asimkilic.loan.application.gen.enums.EnumCreditStatus.APPROVED;
import static com.asimkilic.loan.application.gen.enums.EnumCreditStatus.DENIED;
import static com.asimkilic.loan.application.gen.message.InfoMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest extends TestSupport {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerEntityService customerEntityService;
    @Mock
    private BaseTurkishRepublicIdNoVerificationService trIdNoVerificationService;
    @Mock
    private CreditService creditService;


    @Test
    void testFindAllUsers_shouldReturnCustomerDtoList() {
        List<Customer> allCustomer = getAllCustomer();
        List<CustomerDto> allCustomerDto = convertToCustomerDto();
        when(customerEntityService.findAllActiveCustomers()).thenReturn(allCustomer);
        List<CustomerDto> finalAllCustomerDto = customerService.findAllUsers();

        assertArrayEquals(allCustomerDto.toArray(), finalAllCustomerDto.toArray());
    }

    @Test
    void testFindCustomerById_whenCustomerIdExist_shouldReturnCustomerDto() {
        Customer customer = getFirstCustomer();
        CustomerDto customerDto = getFirstCustomerDto();
        when(customerEntityService.findById("UUID-1")).thenReturn(Optional.of(customer));
        CustomerDto finalCustomerDto = customerService.findCustomerById("UUID-1");
        assertEquals(finalCustomerDto, customerDto);

    }

    @Test
    public void testFindById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerEntityService.findById("UUID-1")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById("UUID-1"));
    }

    @Test
    void testFindCustomerByTurkishRepublicIdNo_whenCustomerTurkishRepublicIdNoExist_shouldReturnCustomerDto() {
        Customer customer = getFirstCustomer();
        CustomerDto customerDto = getFirstCustomerDto();
        when(customerEntityService.findCustomerByTurkishRepublicIdNo("10020030040")).thenReturn(Optional.of(customer));
        CustomerDto finalCustomerDto = customerService.findCustomerByTurkishRepublicIdNo("10020030040");
        assertEquals(finalCustomerDto, customerDto);
    }

    @Test
    void testFindCustomerByTurkishRepublicIdNo_whenCustomerTurkishRepublicIdNoDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerEntityService.findCustomerByTurkishRepublicIdNo("10020030040")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerByTurkishRepublicIdNo("10020030040"));
    }

    @Test
    void testExistsCustomerByTurkishRepublicIdNo_whenCustomerTurkishRepublicIdNoExist_shouldReturnTrue() {
        when(customerEntityService.existsCustomerByTurkishRepublicIdNo("10020030040")).thenReturn(true);
        boolean result = customerService.existsCustomerByTurkishRepublicIdNo("10020030040");
        assertTrue(result);
    }

    @Test
    void testExistsCustomerByTurkishRepublicIdNo_whenCustomerTurkishRepublicIdNoDoesNotExist_shouldReturnFalse() {
        when(customerEntityService.existsCustomerByTurkishRepublicIdNo("10020030040")).thenReturn(false);
        boolean result = customerService.existsCustomerByTurkishRepublicIdNo("10020030040");
        assertFalse(result);
    }

    @Test
    void testExistsCustomerByEmail_whenCustomerEmailExist_shouldReturnTrue() {
        when(customerEntityService.existsCustomerByEmail("customer-1@customer.com")).thenReturn(true);
        boolean result = customerService.existsCustomerByEmail("customer-1@customer.com");
        assertTrue(result);
    }

    @Test
    void testExistsCustomerByEmail_whenCustomerEmailDoesNotExist_shouldReturnFalse() {
        when(customerEntityService.existsCustomerByEmail("customer-1@customer.com")).thenReturn(false);
        boolean result = customerService.existsCustomerByEmail("customer-1@customer.com");
        assertFalse(result);
    }

    @Test
    void testExistsCustomerById_whenCustomerIdExist_shouldReturnTrue() {
        when(customerEntityService.existsActiveCustomerById("UUID-1")).thenReturn(true);
        boolean result = customerService.existsCustomerById("UUID-1");
        assertTrue(result);
    }

    @Test
    void testExistsCustomerById_whenCustomerIdDoesNotExist_shouldReturnFalse() {
        when(customerEntityService.existsActiveCustomerById("UUID-1")).thenReturn(false);
        boolean result = customerService.existsCustomerById("UUID-1");
        assertFalse(result);
    }

    @Test
    void testExistCustomerByPrimaryPhone_whenCustomerPrimaryPhoneExist_shouldReturnTrue() {
        when(customerEntityService.existsCustomerByPrimaryPhone("5329998877")).thenReturn(true);
        boolean result = customerService.existsCustomerByPrimaryPhone("5329998877");
        assertTrue(result);
    }

    @Test
    void testExistCustomerByPrimaryPhone_whenCustomerPrimaryPhoneDoesNotExist_shouldReturnFalse() {
        when(customerEntityService.existsCustomerByPrimaryPhone("5329998877")).thenReturn(false);
        boolean result = customerService.existsCustomerByPrimaryPhone("5329998877");
        assertFalse(result);
    }

    @Test
    void testDeleteCustomer_whenCustomerDoesNotExist_shouldThrowCustomerNotFountException() {
        CustomerDeleteRequestDto deleteRequestDto = getFirstCustomerDeleteRequestDto();
        when(customerEntityService.findCustomerByTurkishRepublicIdNo("10020030040")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(deleteRequestDto));
    }

    @Test
    void testDeleteCustomer_whenCustomerExist_shouldSetCustomerStatusDeleted() {
        CustomerDeleteRequestDto deleteRequestDto = getFirstCustomerDeleteRequestDto();
        Customer customer = getFirstCustomer();
        when(customerEntityService.findCustomerByTurkishRepublicIdNo("10020030040")).thenReturn(Optional.of(customer));
        customerService.deleteCustomer(deleteRequestDto);
        assertNotNull(customer.getUpdateTime());
        assertEquals(customer.getStatus(), EnumCustomerStatus.DELETED);
    }

    @Test
    void testValidateUpdateCustomerEmailCredentialNotInUse_whenUpdateEmailIsNull_shouldDoNothing() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        customerUpdateRequestDto.setEmail(null);
        verify(customerEntityService, never()).validateUpdateCustomerEmailCredentialNotInUse(any(), any());
    }

    @Test
    void testValidateUpdateCustomerEmailCredentialNotInUse_whenUpdateEmailIsNotNull_shouldCallCustomerEntityService() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        when(customerEntityService.validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto.getId(), customerUpdateRequestDto.getEmail())).thenReturn(Boolean.FALSE);

        customerService.validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto);

        verify(customerEntityService, times(1)).validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto.getId(), customerUpdateRequestDto.getEmail());
    }

    @Test
    void testValidateUpdateCustomerEmailCredentialNotInUse_whenUpdateEmailIsNotNullAndInUseAnotherCustomer_shouldThrowEmailIsAlreadySavedException() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        when(customerEntityService.validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto.getId(), customerUpdateRequestDto.getEmail())).thenReturn(Boolean.TRUE);
        assertThrows(EmailIsAlreadySavedException.class, () -> customerService.validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto));
    }

    @Test
    void testValidateUpdateCustomerPrimaryPhoneCredentialNotInUse_whenUpdatePrimaryPhoneIsNull_shouldDoNothing() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        customerUpdateRequestDto.setPrimaryPhone(null);
        verify(customerEntityService, never()).validateUpdateCustomerPrimaryPhoneCredentialNotInUse(any(), any());
    }

    @Test
    void testValidateUpdateCustomerPrimaryPhoneCredentialsNotInUse_whenUpdatePrimaryPhoneIsNotNullAndInUseAnotherCustomer_shouldThrowPhoneIsAlreadySavedException() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        when(customerEntityService.validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customerUpdateRequestDto.getId(), customerUpdateRequestDto.getPrimaryPhone())).thenReturn(Boolean.TRUE);
        PhoneIsAlreadySavedException ex = assertThrows(PhoneIsAlreadySavedException.class, () -> customerService.validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customerUpdateRequestDto));
        assertEquals(PHONE_NUMBER_IS_ALREADY_SAVED, ex.getMessage());
    }

    @Test
    void testValidateUpdateCustomerPrimaryPhoneCredentialsNotInUse_whenUpdatePrimaryPhoneIsNotNull_shouldCallCustomerEntityService() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        when(customerEntityService.validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customerUpdateRequestDto.getId(), customerUpdateRequestDto.getPrimaryPhone())).thenReturn(Boolean.FALSE);
        customerService.validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customerUpdateRequestDto);
        verify(customerEntityService, times(1)).validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customerUpdateRequestDto.getId(), customerUpdateRequestDto.getPrimaryPhone());
    }

    @Test
    void testFindCreditResult_whenCreditResultRequestDtoDateOfBirthDoesNotMatchCustomerDateOfBirth_shouldThrowIllegalCustomerUpdateArgumentException() {
        Customer customer = getFirstCustomer();
        CreditResultRequestDto creditResultRequestDto = getSecondCustomerCreditRequestDto();
        when(customerEntityService.findCustomerByTurkishRepublicIdNo(creditResultRequestDto.getTurkishRepublicIdNo())).thenReturn(Optional.of(customer));
        IllegalCustomerUpdateArgumentException ex = assertThrows(IllegalCustomerUpdateArgumentException.class, () -> customerService.findCreditResult(creditResultRequestDto));
        assertEquals(CUSTOMER_ARGUMENTS_INVALID, ex.getMessage());

    }

    @Test
    void testFindCreditResult_whenCreditResultRequestDtoDateOfBirthDoesMatchCustomerDateOfBirth_shouldReturnBaseCreditResponse() {
        Customer customer = getFirstCustomer();
        CreditResultRequestDto creditResultRequestDto = getFirstCustomerCreditRequestDto();


        when(customerEntityService.findCustomerByTurkishRepublicIdNo(creditResultRequestDto.getTurkishRepublicIdNo())).thenReturn(Optional.of(customer));
        ApprovedCreditResponse app = new ApprovedCreditResponse();
        when(creditService.findCreditResult(creditResultRequestDto)).thenReturn(app);
        BaseCreditResponse creditResult = customerService.findCreditResult(creditResultRequestDto);


        verify(creditService, times(1)).findCreditResult(creditResultRequestDto);
        assertTrue(creditResult.getResponse() == DENIED || creditResult.getResponse() == APPROVED);
    }

    @Test
    void testUpdateCustomer_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        CustomerUpdateRequestDto updateRequestDto = getFirstCustomerUpdateRequestDto();
        when(customerEntityService.findById(updateRequestDto.getId())).thenReturn(Optional.empty());
        CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(updateRequestDto));
        assertEquals(CUSTOMER_NOT_FOUND, ex.getMessage());
    }

    @Test
    void testUpdateCustomer_whenCustomerIdExist_shouldUpdateUser() {
        CustomerUpdateRequestDto updateRequestDto = getFirstCustomerUpdateRequestDto();
        Customer customer = getFirstCustomer();
        when(customerEntityService.findById(updateRequestDto.getId())).thenReturn(Optional.of(customer));
        when(customerEntityService.save(customer)).thenReturn(customer);

        CustomerDto customerDto = customerService.updateCustomer(updateRequestDto);

        verify(customerEntityService, times(1)).save(customer);
        assertEquals(customerDto.getUpdateTime().toLocalDate(), LocalDate.now());
        assertEquals(customerDto.getUpdateTime().toLocalTime().getMinute(), LocalTime.now().getMinute());
        assertEquals(customerDto.getId(), updateRequestDto.getId());
    }

    @Test
    void testValidateCustomerTurkishRepublicIdNo_whenCustomerTurkishRepublicIdNoNotVerified_shouldThrowIllegalCustomerUpdateArgumentException() {
        Customer customer = getFirstCustomer();
        VerifyCustomerTurkishRepublicIdNoRequestDto verifyDto = getFirstCustomerVerifyTurkishRepublicIdNoRequestDto();
        when(trIdNoVerificationService.verifyTurkishRepublicIdNo(verifyDto)).thenReturn(Boolean.FALSE);
        IllegalCustomerUpdateArgumentException ex = assertThrows(IllegalCustomerUpdateArgumentException.class, () -> customerService.validateCustomerTurkishRepublicIdNo(customer));
        assertEquals(CUSTOMER_CREDENTIALS_ARE_NOT_VALID, ex.getMessage());
    }

    @Test
    void testCheckTurkishRepublicIdNoIsValidForNewCustomer_whenTurkishRepublicIdNoAlreadySaved_shouldThrowTurkishRepublicIdNoIsAlreadySavedException() {
        String turkishRepublicIdNo = "10020030040";
        when(customerEntityService.existsCustomerByTurkishRepublicIdNo(turkishRepublicIdNo)).thenReturn(Boolean.TRUE);
        TurkishRepublicIdNoIsAlreadySavedException ex = assertThrows(TurkishRepublicIdNoIsAlreadySavedException.class, () -> customerService.checkTurkishRepublicIdNoIsValidForNewCustomer(turkishRepublicIdNo));
        assertEquals(TURKISH_REPUBLIC_ID_NO_IS_ALREADY_TAKEN, ex.getMessage());
    }

    @Test
    void testCheckEmailAddressIsValidForNewCustomer_whenEmailAddressIsAlreadySaved_shouldThrowEmailIsAlreadySavedException() {
        String email = "customer-1@customer.com";
        when(customerEntityService.existsCustomerByEmail(email)).thenReturn(Boolean.TRUE);
        EmailIsAlreadySavedException ex = assertThrows(EmailIsAlreadySavedException.class, () -> customerService.checkEmailAddressIsValidForNewCustomer(email));
        assertEquals(EMAIL_IS_ALREADY_SAVED, ex.getMessage());
    }

    @Test
    void testCheckPrimaryPhoneIsValidForNewCustomer_whenPrimaryPhoneIsAlreadySaved_shouldThrowPhoneIsAlreadySavedException() {
        String primaryPhone = "5329998877";
        when(customerEntityService.existsCustomerByPrimaryPhone(primaryPhone)).thenReturn(Boolean.TRUE);
        PhoneIsAlreadySavedException ex = assertThrows(PhoneIsAlreadySavedException.class, () -> customerService.checkPrimaryPhoneIsValidForNewCustomer(primaryPhone));
        assertEquals(PHONE_NUMBER_IS_ALREADY_SAVED, ex.getMessage());
    }

    @Test
    void saveNewCustomer() {
    }


}