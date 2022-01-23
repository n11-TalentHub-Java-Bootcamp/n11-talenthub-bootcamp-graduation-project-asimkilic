package com.asimkilic.loan.application.converter.credit;

import com.asimkilic.loan.application.dto.credit.CreditScoreInquiryRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    CreditScoreInquiryRequestDto convertToCreditScoreInquiryRequestDto(String turkishRepublicIdNo);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creditScoreAtTheTimeOfApplication", source = "creditScore")
    @Mapping(target = "creditStatus", source = "denied")
    @Mapping(target = "amountOfGuaranteeAtTheTimeOfApplication", source = "customerDto.amountOfGuarantee")
    @Mapping(target = "salaryAtTheTimeOfApplication", source = "customerDto.monthlySalary")
    @Mapping(target = "creditConstraint", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    @Mapping(target = "creditLimit", ignore = true)
    @Mapping(target = "customer.id", source = "customerDto.id")
    Credit convertToCreditForDenied(CustomerDto customerDto, EnumCreditStatus denied, BigDecimal creditScore);
}
