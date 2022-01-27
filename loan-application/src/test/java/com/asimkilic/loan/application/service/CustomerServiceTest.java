package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.TestSupport;
import com.asimkilic.loan.application.converter.customer.CustomerMapper;
import com.asimkilic.loan.application.converter.customer.CustomerMapperImpl;
import com.asimkilic.loan.application.dto.customer.CustomerDeleteRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.exception.customer.CustomerNotFoundException;
import com.asimkilic.loan.application.exception.customer.EmailIsAlreadySavedException;
import com.asimkilic.loan.application.exception.customer.PhoneIsAlreadySavedException;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
import com.asimkilic.loan.application.gen.service.turkishrepublicidno.BaseTurkishRepublicIdNoVerificationService;
import com.asimkilic.loan.application.service.entityservice.customer.CustomerEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


import static com.asimkilic.loan.application.converter.customer.CustomerMapper.INSTANCE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
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

    @Mock
    private Clock clock = Clock.systemUTC();


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

        verify(customerEntityService, times(1)).validateUpdateCustomerEmailCredentialNotInUse(any(), any());
    }

    @Test
    void testValidateUpdateCustomerEmailCredentialNotInUse_whenUpdateEmailNotNullAndInUseAnotherCustomer_shouldThrowEmailIsAlreadySavedException() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        when(customerEntityService.validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto.getId(), customerUpdateRequestDto.getEmail())).thenReturn(Boolean.TRUE);
        assertThrows(EmailIsAlreadySavedException.class, () -> customerService.validateUpdateCustomerEmailCredentialNotInUse(customerUpdateRequestDto));
    }


    @Test
    void saveNewCustomer() {
    }

    @Test
    void updateCustomer() {
    }


    @Test
    void findCreditResult() {
    }


}