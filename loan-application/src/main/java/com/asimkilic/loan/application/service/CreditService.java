package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.converter.credit.CreditConstraintMapper;
import com.asimkilic.loan.application.dto.credit.CreditCalculationRequestDto;
import com.asimkilic.loan.application.dto.credit.CreditScoreInquiryRequestDto;
import com.asimkilic.loan.application.dto.credit.DeniedCreditResponse;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.gen.service.BaseCreditCalculationService;
import com.asimkilic.loan.application.gen.service.BaseCreditScoreInquiryService;
import com.asimkilic.loan.application.service.entityservice.credit.CreditEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.asimkilic.loan.application.converter.credit.CreditMapper.*;

@Service
@RequiredArgsConstructor
class CreditService {
    private final CreditEntityService creditEntityService;
    private final Clock clock;
    private final BaseCreditScoreInquiryService creditScoreService;
    private final BaseCreditCalculationService creditCalculator;

    public BaseCreditResponse applyCredit(CustomerDto customerDto) {
        CreditScoreInquiryRequestDto creditScoreInquiryRequestDto = INSTANCE.convertToCreditScoreInquiryRequestDto(customerDto.getTurkishRepublicIdNo());
        BigDecimal creditScore = creditScoreService.findCreditScore(creditScoreInquiryRequestDto);
        boolean isCustomerSuitable = checkIsCustomerSuitableToCreditIfNotSaveIt(customerDto, creditScore);
        if (!isCustomerSuitable) {
            return new DeniedCreditResponse();
        }
        CreditConstraint suitableCreditConstraint = creditEntityService.findSuitableCreditConstraint(customerDto.getMonthlySalary(), creditScore);

        CreditCalculationRequestDto creditCalculationRequestDto = CreditConstraintMapper.INSTANCE.convertToCreditCalculationRequestDto(suitableCreditConstraint, customerDto.getMonthlySalary(), customerDto.getAmountOfGuarantee());
// TODO CHECK CALCULATION AND CHECK REQUEST WITH DATABASE NATIVE QUERY
        BigDecimal calculate = creditCalculator.calculate(creditCalculationRequestDto);


        return null;
    }

    private boolean checkIsCustomerSuitableToCreditIfNotSaveIt(CustomerDto customerDto, BigDecimal creditScore) {
        boolean hasCredit = doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(customerDto.getTurkishRepublicIdNo());
        if (hasCredit || creditScore.compareTo(BigDecimal.valueOf(500)) < 0) {
            Credit credit = INSTANCE.convertToCreditForDenied(customerDto, EnumCreditStatus.DENIED, creditScore);
            credit.setCreationTime(getLocalDateTimeNow());
            creditEntityService.save(credit);
            // TODO : Call sms api
            return false;
        }
        return true;
    }

    private void saveDeniedCredit(CustomerDto customerDto, BigDecimal creditScore) {

    }

    private boolean doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return creditEntityService.doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
    }


}
/*
Kredinin neticelenmesi sonucunda ilgili başvuru veritabanına kaydedilir. Daha sonrasında
ise ilgili telefon numarasına bilgilendirme SMS’i gönderilir ve endpoint’ten onay durum
bilgisi (red veya onay), limit bilgisi dönülür.

Gerçekleştirilmiş bir kredi başvurusu sadece kimlik numarası ve doğum tarihi bilgisi ile
sorgulanabilir. Doğum tarihi ve kimlik bilgisi eşleşmezse sorgulanamamalıdır.
 */