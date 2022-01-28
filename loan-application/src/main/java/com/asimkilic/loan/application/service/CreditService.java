package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.converter.credit.CreditConstraintMapper;
import com.asimkilic.loan.application.dto.credit.*;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.exception.credit.CreditNotFoundException;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.gen.service.credit.BaseCreditCalculationService;
import com.asimkilic.loan.application.gen.service.credit.BaseCreditScoreInquiryService;
import com.asimkilic.loan.application.gen.service.notification.sms.SmsHandler;
import com.asimkilic.loan.application.service.entityservice.credit.CreditEntityService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;


import static com.asimkilic.loan.application.converter.credit.CreditMapper.*;
import static com.asimkilic.loan.application.gen.message.InfoMessage.*;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditEntityService creditEntityService;
    private final Clock clock;
    private final BaseCreditScoreInquiryService creditScoreService;
    private final BaseCreditCalculationService creditCalculator;
    private final SmsHandler smsHandler;

    public BaseCreditResponse applyCredit(CustomerDto customerDto) {
        CreditScoreInquiryRequestDto creditScoreInquiryRequestDto = INSTANCE.convertToCreditScoreInquiryRequestDto(customerDto.getTurkishRepublicIdNo());
        BigDecimal creditScore = creditScoreService.findCreditScore(creditScoreInquiryRequestDto);
        boolean hasCredit = doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(customerDto.getTurkishRepublicIdNo());
        if (hasCredit || creditScore.compareTo(BigDecimal.valueOf(500)) < 0) {
            saveDeniedCreditApplicationAndSendSms(customerDto, creditScore);
            return new DeniedCreditResponse();
        }
        CreditConstraint suitableCreditConstraint = creditEntityService.findSuitableCreditConstraint(customerDto.getMonthlySalary(), creditScore);

        CreditCalculationRequestDto creditCalculationRequestDto = CreditConstraintMapper.INSTANCE.convertToCreditCalculationRequestDto(suitableCreditConstraint, customerDto.getMonthlySalary(), customerDto.getAmountOfGuarantee());
        BigDecimal calculateTotalCreditBalance = creditCalculator.calculate(creditCalculationRequestDto);

        Credit approvedCredit = INSTANCE.convertToCreditForApproved(customerDto, suitableCreditConstraint, calculateTotalCreditBalance, EnumCreditStatus.APPROVED, creditScore);
        approvedCredit.setCreationTime(getLocalDateTimeNow());
        creditEntityService.save(approvedCredit);
        smsHandler.sendSms(customerDto.getPrimaryPhone(), CREDIT_APPLICATION_IS_APPROVED, approvedCredit.getCreditLimit(), ThreadContext.get("requestid"));
        return new ApprovedCreditResponse(calculateTotalCreditBalance);
    }


    private void saveDeniedCreditApplicationAndSendSms(CustomerDto customerDto, BigDecimal creditScore) {
        Credit credit = INSTANCE.convertToCreditForDenied(customerDto, EnumCreditStatus.DENIED, creditScore);
        credit.setCreationTime(getLocalDateTimeNow());
        creditEntityService.save(credit);
        smsHandler.sendSms(customerDto.getPrimaryPhone(), CREDIT_APPLICATION_IS_DENIED, ThreadContext.get("requestid"));

    }

    public BaseCreditResponse findCreditResult(CreditResultRequestDto creditResultRequestDto) {

        Credit credit = creditEntityService
                .findCreditResultByTurkishRepublicIdNo(creditResultRequestDto.getTurkishRepublicIdNo())
                .orElseThrow(() -> new CreditNotFoundException(CREDIT_NOT_FOUND));

        return credit::getCreditStatus;

    }

    private boolean doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return creditEntityService.doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    private LocalDateTime getLocalDateTimeNow() {
        //Instant instant = clock.instant();
        //return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
        return LocalDateTime.now();
    }


}