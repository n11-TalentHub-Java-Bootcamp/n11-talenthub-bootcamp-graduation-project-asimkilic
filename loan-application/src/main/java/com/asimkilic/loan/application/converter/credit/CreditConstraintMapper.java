package com.asimkilic.loan.application.converter.credit;

import com.asimkilic.loan.application.dto.credit.CreditCalculationRequestDto;
import com.asimkilic.loan.application.entity.CreditConstraint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CreditConstraintMapper {
    CreditConstraintMapper INSTANCE = Mappers.getMapper(CreditConstraintMapper.class);


    @Mapping(source = "clientAmountOfGuarantee", target = "clientAmountOfGuarantee")
    @Mapping(source = "clientSalary", target = "clientSalary")
    CreditCalculationRequestDto convertToCreditCalculationRequestDto(CreditConstraint creditConstraint, BigDecimal clientSalary, BigDecimal clientAmountOfGuarantee);
}
