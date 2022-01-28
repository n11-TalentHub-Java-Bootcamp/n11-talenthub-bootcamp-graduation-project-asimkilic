package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.TestSupport;
import com.asimkilic.loan.application.dto.credit.ApprovedCreditResponse;
import com.asimkilic.loan.application.dto.credit.CreditResultRequestDto;
import com.asimkilic.loan.application.dto.credit.DeniedCreditResponse;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.exception.credit.CreditNotFoundException;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.gen.message.InfoMessage;
import com.asimkilic.loan.application.gen.service.credit.BaseCreditCalculationService;
import com.asimkilic.loan.application.gen.service.credit.BaseCreditScoreInquiryService;
import com.asimkilic.loan.application.gen.service.notification.sms.SmsHandler;
import com.asimkilic.loan.application.service.entityservice.credit.CreditEntityService;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.Optional;

import static com.asimkilic.loan.application.gen.message.InfoMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest extends TestSupport {
    @InjectMocks
    private CreditService creditService;
    @Mock
    private CreditEntityService creditEntityService;
    @Mock
    private BaseCreditScoreInquiryService creditScoreService;
    @Mock
    private BaseCreditCalculationService creditCalculator;
    @Mock
    private SmsHandler smsHandler;

    @Test
    void testApplyCredit_whenCustomerHasAnotherCredit_shouldReturnDeniedCreditResponseAndSendSms() {
        CustomerDto customerDto = getFirstCustomerDto();
        when(creditEntityService.doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(customerDto.getTurkishRepublicIdNo())).thenReturn(true);
        BaseCreditResponse creditResponse = creditService.applyCredit(customerDto);
        DeniedCreditResponse expectedResponse = new DeniedCreditResponse();
        verify(creditEntityService, times(1)).save(any());
        verify(smsHandler, times(1)).sendSms(customerDto.getPrimaryPhone(), CREDIT_APPLICATION_IS_DENIED, ThreadContext.get("requestid"));
        assertEquals(expectedResponse, creditResponse);
    }

    @Test
    void testApplyCredit_whenCustomerCreditScoreIsNotSuitable_shouldReturnDeniedCreditResponseAndSendSms() {
        CustomerDto customerDto = getFirstCustomerDto();
        when(creditScoreService.findCreditScore(any())).thenReturn(BigDecimal.ZERO);
        BaseCreditResponse creditResponse = creditService.applyCredit(customerDto);
        DeniedCreditResponse expectedResponse = new DeniedCreditResponse();
        verify(creditEntityService, times(1)).save(any());
        verify(smsHandler, times(1)).sendSms(customerDto.getPrimaryPhone(), CREDIT_APPLICATION_IS_DENIED, ThreadContext.get("requestid"));
        assertEquals(expectedResponse, creditResponse);
    }

    @Test
    void testApplyCredit_whenCustomerCreditScoreIsSuitableAndDoesNotHaveAnotherCredit_shouldSaveItAndSendSmsAndReturnApprovedCreditScoreWithCreditLimit() {
        CustomerDto customerDto = getFirstCustomerDto();
        CreditConstraint creditConstraint = getFirstCustomerCreditConstraint();
        when(creditScoreService.findCreditScore(any())).thenReturn(BigDecimal.valueOf(1500));
        when(creditEntityService.doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(customerDto.getTurkishRepublicIdNo())).thenReturn(false);
        when(creditEntityService.findSuitableCreditConstraint(customerDto.getMonthlySalary(), BigDecimal.valueOf(1500))).thenReturn(creditConstraint);
        when(creditCalculator.calculate(any())).thenReturn(BigDecimal.valueOf(10200));
        BaseCreditResponse creditResponse = creditService.applyCredit(customerDto);
        verify(creditEntityService, times(1)).save(any());
        verify(smsHandler, times(1)).sendSms(customerDto.getPrimaryPhone(), CREDIT_APPLICATION_IS_APPROVED, BigDecimal.valueOf(10200), ThreadContext.get("requestid"));
        assertEquals(creditResponse.getResponse(), EnumCreditStatus.APPROVED);
        assertEquals(((ApprovedCreditResponse) creditResponse).getCreditLimit(), BigDecimal.valueOf(10200));
    }


    @Test
    void testFindCreditResult_whenCreditDoesNotExist_shouldThrowCreditNotFoundException() {
        CreditResultRequestDto creditResultRequestDto = getFirstCustomerCreditResultRequestDto();
        when(creditEntityService.findCreditResultByTurkishRepublicIdNo(creditResultRequestDto.getTurkishRepublicIdNo())).thenReturn(Optional.empty());
        CreditNotFoundException ex = assertThrows(CreditNotFoundException.class, () -> creditService.findCreditResult(creditResultRequestDto));
        assertEquals(CREDIT_NOT_FOUND, ex.getMessage());
    }

    @Test
    void testFindCreditResult_whenCreditExistAndApproved_shouldReturnBaseCreditResponseWithApproved() {
        CreditResultRequestDto creditResultRequestDto = getFirstCustomerCreditResultRequestDto();
        Credit credit = getFirstCustomerApprovedCredit();
        when(creditEntityService.findCreditResultByTurkishRepublicIdNo(creditResultRequestDto.getTurkishRepublicIdNo())).thenReturn(Optional.of(credit));
        BaseCreditResponse creditResult = creditService.findCreditResult(creditResultRequestDto);
        assertEquals(creditResult.getResponse(), EnumCreditStatus.APPROVED);

    }

    @Test
    void testFindCreditResult_whenCreditExistAndDenied_shouldReturnBaseCreditResponseWithDenied() {
        CreditResultRequestDto creditResultRequestDto = getFirstCustomerCreditResultRequestDto();
        Credit credit = getFirstCustomerDeniedCredit();
        when(creditEntityService.findCreditResultByTurkishRepublicIdNo(creditResultRequestDto.getTurkishRepublicIdNo())).thenReturn(Optional.of(credit));
        BaseCreditResponse creditResult = creditService.findCreditResult(creditResultRequestDto);
        assertEquals(creditResult.getResponse(), EnumCreditStatus.DENIED);


    }


}