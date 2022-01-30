package com.asimkilic.loan.application.controller;

import com.asimkilic.loan.application.TestSupport;
import com.asimkilic.loan.application.dto.credit.CreditResultRequestDto;
import com.asimkilic.loan.application.dto.customer.*;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.gen.service.turkishrepublicidno.BaseTurkishRepublicIdNoVerificationService;
import com.asimkilic.loan.application.gen.service.turkishrepublicidno.TrIdNoVerificationService;
import com.asimkilic.loan.application.service.CreditService;
import com.asimkilic.loan.application.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends TestSupport {

    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    @Mock
    private BaseTurkishRepublicIdNoVerificationService baseTurkishRepublicIdNoVerificationService;
    @Mock
    private CreditService creditService;

    @Test
    void testGetAllUsers_shouldListCustomers() {
        ResponseEntity<List<CustomerDto>> allUsers = customerController.getAllUsers();
        assertEquals(HttpStatus.valueOf(200), allUsers.getStatusCode());
        verify(customerService, times(1)).findAllUsers();
    }

    @Test
    void testSaveNewCustomer_shouldSaveCustomer() {
        CustomerSaveRequestDto customerSaveRequestDto = getCustomerSaveRequestDto();
        ResponseEntity<BaseCreditResponse> baseCreditResponseResponseEntity = customerController.saveNewCustomer(customerSaveRequestDto);
        assertEquals(HttpStatus.valueOf(200), baseCreditResponseResponseEntity.getStatusCode());
        verify(customerService, times(1)).saveNewCustomer(customerSaveRequestDto);

    }

    @Test
    void testUpdateCustomer_shouldUpdateCustomer() {
        CustomerUpdateRequestDto customerUpdateRequestDto = getFirstCustomerUpdateRequestDto();
        ResponseEntity<CustomerDto> customerDtoResponseEntity = customerController.updateCustomer(customerUpdateRequestDto);

        assertEquals(HttpStatus.valueOf(200), customerDtoResponseEntity.getStatusCode());
        verify(customerService, times(1)).updateCustomer(customerUpdateRequestDto);
    }

    @Test
    void testDeleteCustomer_shouldDeleteCustomer() {
        CustomerDeleteRequestDto customerDeleteRequestDto = getFirstCustomerDeleteRequestDto();
        ResponseEntity responseEntity = customerController.deleteCustomer(customerDeleteRequestDto);

        assertEquals(HttpStatus.valueOf(204), responseEntity.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(customerDeleteRequestDto);

    }

    @Test
    void testGetCustomerById_shouldReturnCustomer() {
        ResponseEntity<CustomerDto> customerById = customerController.getCustomerById("UUID-1");
        assertEquals(HttpStatus.valueOf(200), customerById.getStatusCode());
        verify(customerService, times(1)).findCustomerById("UUID-1");

    }

    @Test
    void testGetCustomerByTurkishRepublicIdNo_shouldReturnCustomer() {
        ResponseEntity<CustomerDto> customerByTurkishRepublicIdNo = customerController.getCustomerByTurkishRepublicIdNo("10020030040");
        assertEquals(HttpStatus.valueOf(200), customerByTurkishRepublicIdNo.getStatusCode());
        verify(customerService, times(1)).findCustomerByTurkishRepublicIdNo("10020030040");

    }

    @Test
    void testGetCreditResult_shouldReturnCreditResult() {
        CreditResultRequestDto creditResultRequestDto = getFirstCustomerCreditResultRequestDto();
        ResponseEntity<BaseCreditResponse> creditResult = customerController.getCreditResult(creditResultRequestDto);
        assertEquals(HttpStatus.valueOf(200), creditResult.getStatusCode());
        verify(customerService, times(1)).findCreditResult(creditResultRequestDto);
    }
    @Test
    void testApplyCreditByTurkishRepublicIdNo_shouldReturnCreditResult(){
        ResponseEntity<BaseCreditResponse> baseCreditResponseResponseEntity = customerController.applyCreditByTurkishRepublicIdNo("10020030040");
        assertEquals(HttpStatus.valueOf(200), baseCreditResponseResponseEntity.getStatusCode());
        verify(customerService, times(1)).applyCreditByTurkishRepublicIdNo("10020030040");
    }
}