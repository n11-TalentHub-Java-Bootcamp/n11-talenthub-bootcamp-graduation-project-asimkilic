package com.asimkilic.loan.application.service.entityservice.credit;

import com.asimkilic.loan.application.TestSupport;
import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.repository.credit.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditEntityServiceTest extends TestSupport {
    @InjectMocks
    private CreditEntityService creditEntityService;
    @Mock
    private CreditRepository creditRepository;

    @Test
    void testDoesCustomerHaveApprovedCreditByTurkishRepublicIdNo_whenCustomerHaveApprovedCredit_shouldReturnTrue() {
        Customer customer = getFirstCustomer();
        when(creditRepository.existsCreditByCustomer_TurkishRepublicIdNoAndCreditStatus(customer.getTurkishRepublicIdNo(), EnumCreditStatus.APPROVED)).thenReturn(true);
        boolean result = creditEntityService.doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(customer.getTurkishRepublicIdNo());
        assertTrue(result);
    }

    @Test
    void testDoesCustomerHaveApprovedCreditByTurkishRepublicIdNo_whenCustomerDontHaveApprovedCredit_shouldReturnFalse() {
        Customer customer = getFirstCustomer();
        when(creditRepository.existsCreditByCustomer_TurkishRepublicIdNoAndCreditStatus(customer.getTurkishRepublicIdNo(), EnumCreditStatus.APPROVED)).thenReturn(false);
        boolean result = creditEntityService.doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(customer.getTurkishRepublicIdNo());
        assertFalse(result);
    }

    @Test
    void testFindSuitableCreditConstraint_shouldReturnCreditConstraint() {
        Customer customer = getFirstCustomer();
        CreditConstraint creditConstraint = getFirstCustomerCreditConstraint();
        when(creditRepository.findSuitableCreditConstraint(any(), any())).thenReturn(creditConstraint);
        CreditConstraint suitableCreditConstraint = creditEntityService.findSuitableCreditConstraint(customer.getMonthlySalary(), BigDecimal.valueOf(500));
        assertEquals(creditConstraint, suitableCreditConstraint);

    }

    @Test
    void testFindCreditResultByTurkishRepublicIdNo_whenCreditIsExist_shouldReturnOptionalWithCredit() {
        Customer customer = getFirstCustomer();
        Credit credit = getFirstCustomerApprovedCredit();
        when(creditRepository.findAllByCustomer_TurkishRepublicIdNoOrderByCreationTimeDesc(customer.getTurkishRepublicIdNo())).thenReturn(Optional.of(credit));
        Optional<Credit> creditResultByTurkishRepublicIdNo = creditEntityService.findCreditResultByTurkishRepublicIdNo(customer.getTurkishRepublicIdNo());
        assertEquals(Optional.of(credit), creditResultByTurkishRepublicIdNo);
        assertNotEquals(Optional.empty(), creditResultByTurkishRepublicIdNo);


    }
}