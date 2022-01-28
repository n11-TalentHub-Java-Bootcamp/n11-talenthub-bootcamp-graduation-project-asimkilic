package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.TestSupport;
import com.asimkilic.loan.application.dto.credit.CreditCalculationRequestDto;
import com.asimkilic.loan.application.gen.message.InfoMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.asimkilic.loan.application.gen.message.InfoMessage.CREDIT_CALCULATOR_PARAMETERS_ARE_NULL;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreditCalculationServiceTest extends TestSupport {

    @InjectMocks
    private CreditCalculationService creditCalculationService;
    @Test
    void testCalculate_whenCreditCalculationRequestDtoIsNull_shouldThrowRuntimeException(){
        CreditCalculationRequestDto requestDto = null;
        RuntimeException ex = assertThrows(RuntimeException.class,() -> creditCalculationService.calculate(requestDto));
        assertEquals(CREDIT_CALCULATOR_PARAMETERS_ARE_NULL,ex.getMessage());
    }
    @Test
    void testCalculate_whenCreditCalculationRequestGiven_shouldCalculateAndReturnIt(){
        CreditCalculationRequestDto creditCalculationRequestDto = CreditCalculationRequestDto
                .builder()
                .clientSalary(BigDecimal.valueOf(5000))
                .clientAmountOfGuarantee(BigDecimal.valueOf(1000))
                .creditLimit(BigDecimal.valueOf(10000))
                .percentageOfGuarantee(BigDecimal.valueOf(10))
                .creditLimitMultiplierCoefficient(BigDecimal.ZERO)
                .build();
        BigDecimal creditResult = creditCalculationService.calculate(creditCalculationRequestDto);

        assertEquals(BigDecimal.valueOf(10100),creditResult);

    }

}