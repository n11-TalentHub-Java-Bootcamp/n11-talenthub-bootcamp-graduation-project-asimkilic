package com.asimkilic.loan.application.service.entityservice.customer;

import com.asimkilic.loan.application.TestSupport;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
import com.asimkilic.loan.application.repository.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerEntityServiceTest extends TestSupport {
    @InjectMocks
    private CustomerEntityService customerEntityService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testFindAllActiveCustomers_whenCustomersExist_shouldReturnAllCustomers() {
        List<Customer> allCustomers = getAllCustomer();
        when(customerRepository.findAllByStatus(EnumCustomerStatus.ACTIVE)).thenReturn(allCustomers);
        List<Customer> allActiveCustomers = customerEntityService.findAllActiveCustomers();
        assertEquals(allCustomers, allActiveCustomers);
    }

    @Test
    void testFindAllActiveCustomers_whenCustomersDontExist_shouldReturnEmptyList() {
        List<Customer> customers = new ArrayList<>();
        when(customerRepository.findAllByStatus(EnumCustomerStatus.ACTIVE)).thenReturn(customers);
        List<Customer> allActiveCustomers = customerEntityService.findAllActiveCustomers();
        assertSame(customers, allActiveCustomers);
    }

    @Test
    void testFindCustomerByTurkishRepublicIdNo_whenCustomerExists_shouldReturnOptionalWithCustomer() {
        Customer customer = getFirstCustomer();
        when(customerRepository.findCustomerByTurkishRepublicIdNoAndStatus(customer.getTurkishRepublicIdNo(), EnumCustomerStatus.ACTIVE)).thenReturn(Optional.of(customer));
        Optional<Customer> customerByTurkishRepublicIdNo = customerEntityService.findCustomerByTurkishRepublicIdNo(customer.getTurkishRepublicIdNo());
        assertEquals(customer, customerByTurkishRepublicIdNo.get());
    }

    @Test
    void testFindCustomerByTurkishRepublicIdNo_whenCustomerDoesNotExist_shouldReturnOptionalEmpty() {

        when(customerRepository.findCustomerByTurkishRepublicIdNoAndStatus("10020030099", EnumCustomerStatus.ACTIVE)).thenReturn(Optional.empty());
        Optional<Customer> customerByTurkishRepublicIdNo = customerEntityService.findCustomerByTurkishRepublicIdNo("10020030099");
        assertEquals(Optional.empty(), customerByTurkishRepublicIdNo);
    }

    @Test
    void testFindCustomerByEmail_whenCustomerExists_shouldReturnOptionalWithCustomer() {
        Customer customer = getFirstCustomer();
        when(customerRepository.findCustomerByEmailAndStatus(customer.getEmail(), EnumCustomerStatus.ACTIVE)).thenReturn(Optional.of(customer));
        Optional<Customer> customerByEmail = customerEntityService.findCustomerByEmail(customer.getEmail());
        assertEquals(Optional.of(customer), customerByEmail);

    }

    @Test
    void testFindCustomerByEmail_whenCustomerDoesNotExist_shouldReturnOptionalEmpty() {
        when(customerRepository.findCustomerByEmailAndStatus("customer-99@customer.com", EnumCustomerStatus.ACTIVE)).thenReturn(Optional.empty());
        Optional<Customer> customerByEmail = customerEntityService.findCustomerByEmail("customer-99@customer.com");
        assertEquals(Optional.empty(), customerByEmail);

    }

    @Test
    void testFindCustomerByTurkishRepublicIdNoAndDateOfBirth_whenCustomerExists_shouldReturnOptionalWithCustomer() {
        Customer customer = getFirstCustomer();
        when(customerRepository.findCustomerByTurkishRepublicIdNoAndDateOfBirthAndStatus(customer.getTurkishRepublicIdNo(), customer.getDateOfBirth(), EnumCustomerStatus.ACTIVE)).thenReturn(Optional.of(customer));
        Optional<Customer> customerByTurkishRepublicIdNoAndDateOfBirth = customerEntityService.findCustomerByTurkishRepublicIdNoAndDateOfBirth(customer.getTurkishRepublicIdNo(), customer.getDateOfBirth());
        assertEquals(Optional.of(customer), customerByTurkishRepublicIdNoAndDateOfBirth);
    }

    @Test
    void testFindCustomerByTurkishRepublicIdNoAndDateOfBirth_whenCustomerDoesNotExist_shouldReturnOptionalEmpty() {
        Customer customer = getFirstCustomer();
        when(customerRepository.findCustomerByTurkishRepublicIdNoAndDateOfBirthAndStatus(customer.getTurkishRepublicIdNo(), customer.getDateOfBirth(), EnumCustomerStatus.ACTIVE)).thenReturn(Optional.empty());
        Optional<Customer> customerByTurkishRepublicIdNoAndDateOfBirth = customerEntityService.findCustomerByTurkishRepublicIdNoAndDateOfBirth(customer.getTurkishRepublicIdNo(), customer.getDateOfBirth());
        assertEquals(Optional.empty(), customerByTurkishRepublicIdNoAndDateOfBirth);
    }

    @Test
    void testExistsCustomerByTurkishRepublicIdNo_whenCustomerExists_shouldReturnTrue() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByTurkishRepublicIdNoAndStatus(customer.getTurkishRepublicIdNo(), EnumCustomerStatus.ACTIVE)).thenReturn(true);
        boolean result = customerEntityService.existsCustomerByTurkishRepublicIdNo(customer.getTurkishRepublicIdNo());
        assertTrue(result);
    }

    @Test
    void testExistsCustomerByTurkishRepublicIdNo_whenCustomerDoesNotExist_shouldReturnFalse() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByTurkishRepublicIdNoAndStatus(customer.getTurkishRepublicIdNo(), EnumCustomerStatus.ACTIVE)).thenReturn(false);
        boolean result = customerEntityService.existsCustomerByTurkishRepublicIdNo(customer.getTurkishRepublicIdNo());
        assertFalse(result);
    }

    @Test
    void testExistsCustomerByEmail_whenCustomerExists_shouldReturnTrue() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByEmailAndStatus(customer.getEmail(), EnumCustomerStatus.ACTIVE)).thenReturn(true);
        boolean result = customerEntityService.existsCustomerByEmail(customer.getEmail());
        assertTrue(result);
    }

    @Test
    void testExistsCustomerByEmail_whenCustomerDoesNotExist_shouldReturnFalse() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByEmailAndStatus(customer.getEmail(), EnumCustomerStatus.ACTIVE)).thenReturn(false);
        boolean result = customerEntityService.existsCustomerByEmail(customer.getEmail());
        assertFalse(result);
    }

    @Test
    void testExistsActiveCustomerById_whenCustomerExistsAndActive_shouldReturnTrue() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByIdAndStatus(customer.getId(), EnumCustomerStatus.ACTIVE)).thenReturn(true);
        boolean result = customerEntityService.existsActiveCustomerById(customer.getId());
        assertTrue(result);
    }

    @Test
    void testExistsActiveCustomerById_whenCustomerDoesNotExist_shouldReturnFalse() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByIdAndStatus(customer.getId(), EnumCustomerStatus.ACTIVE)).thenReturn(false);
        boolean result = customerEntityService.existsActiveCustomerById(customer.getId());
        assertFalse(result);
    }

    @Test
    void testExistsCustomerByPrimaryPhone_whenCustomerExists_shouldReturnTrue() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByPrimaryPhoneAndStatus(customer.getPrimaryPhone(), EnumCustomerStatus.ACTIVE)).thenReturn(true);
        boolean result = customerEntityService.existsCustomerByPrimaryPhone(customer.getPrimaryPhone());
        assertTrue(result);
    }

    @Test
    void testExistsCustomerByPrimaryPhone_whenCustomerDoesNotExist_shouldReturnFalse() {
        Customer customer = getFirstCustomer();
        when(customerRepository.existsCustomerByPrimaryPhoneAndStatus(customer.getPrimaryPhone(), EnumCustomerStatus.ACTIVE)).thenReturn(false);
        boolean result = customerEntityService.existsCustomerByPrimaryPhone(customer.getPrimaryPhone());
        assertFalse(result);
    }

    @Test
    void testValidateUpdateCustomerEmailCredentialNotInUse_whenEmailIsInUseAnotherCustomer_shouldReturnTrue() {
        Customer customer = getFirstCustomer();
        when(customerRepository.validateUpdateCustomerEmailCredentialNotInUse(customer.getId(), customer.getEmail())).thenReturn(true);
        boolean result = customerEntityService.validateUpdateCustomerEmailCredentialNotInUse(customer.getId(), customer.getEmail());
        assertTrue(result);
    }

    @Test
    void testValidateUpdateCustomerEmailCredentialNotInUse_whenEmailIsNotInUseAnotherCustomer_shouldReturnFalse() {
        Customer customer = getFirstCustomer();
        when(customerRepository.validateUpdateCustomerEmailCredentialNotInUse(customer.getId(), customer.getEmail())).thenReturn(false);
        boolean result = customerEntityService.validateUpdateCustomerEmailCredentialNotInUse(customer.getId(), customer.getEmail());
        assertFalse(result);
    }

    @Test
    void testValidateUpdateCustomerPrimaryPhoneCredentialNotInUse_whenPrimaryPhoneIsInUseAnotherCustomer_shouldReturnTrue() {
        Customer customer = getFirstCustomer();
        when(customerRepository.validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customer.getId(), customer.getPrimaryPhone())).thenReturn(true);
        boolean result = customerEntityService.validateUpdateCustomerPrimaryPhoneCredentialNotInUse(customer.getId(), customer.getPrimaryPhone());
        assertTrue(result);
    }

    @Test
    void testFindDeletedCustomerIfExist_whenCustomerIsExistAndDeleted_shouldReturnOptionalWithCustomer() {
        Customer customer = getFirstCustomer();
        when(customerRepository.findCustomerByTurkishRepublicIdNoAndStatus(customer.getTurkishRepublicIdNo(), EnumCustomerStatus.DELETED)).thenReturn(Optional.of(customer));
        Optional<Customer> deletedCustomerIfExist = customerEntityService.findDeletedCustomerIfExist(customer.getTurkishRepublicIdNo());
        assertEquals(Optional.of(customer), deletedCustomerIfExist);

    }
    @Test
    void testFindDeletedCustomerIfExist_whenCustomerIsNotExist_shouldReturnOptionalEmpty() {
        Customer customer = getFirstCustomer();
        when(customerRepository.findCustomerByTurkishRepublicIdNoAndStatus(customer.getTurkishRepublicIdNo(), EnumCustomerStatus.DELETED)).thenReturn(Optional.empty());
        Optional<Customer> deletedCustomerIfExist = customerEntityService.findDeletedCustomerIfExist(customer.getTurkishRepublicIdNo());
        assertEquals(Optional.empty(), deletedCustomerIfExist);

    }
}
