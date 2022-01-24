package com.asimkilic.loan.application.repository.credit;

import com.asimkilic.loan.application.dto.credit.CreditCalculationRequestDto;
import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, String> {
    boolean existsCreditByCustomer_TurkishRepublicIdNoAndCreditStatus(String turkishRepublicIdNo, EnumCreditStatus creditStatus);


    @Query(
            value = " select  " +
                    " new com.asimkilic.loan.application.entity.CreditConstraint( " +
                    " cs.id, cs.creditLimit, cs.percentageOfGuarantee, cs.creditLimitMultiplierCoefficient, " +
                    " cs.creditScoreLowerLimit, cs.creditScoreUpperLimit, cs.salaryLowerLimit, cs.salaryUpperLimit " +
                    ")" +
                    " from CreditConstraint cs " +
                    " where ( ?2 >= cs.creditScoreLowerLimit and ?2 <= cs.creditScoreUpperLimit ) " +
                    " and ( ( ?1 >= cs.salaryLowerLimit or cs.salaryLowerLimit is null ) " +
                    " and ( ?1 <= cs.salaryUpperLimit or cs.salaryUpperLimit is null ) ) " +
                    " order by cs.creditScoreLowerLimit desc, cs.salaryLowerLimit desc"

    )
    CreditConstraint findSuitableCreditConstraint(BigDecimal customerSalary, BigDecimal creditScore);

    Optional<Credit> findAllByCustomer_TurkishRepublicIdNoOrderByCreationTimeDesc(String turkishRepublicIdNo);

}
/*

Optional<Credit> findCreditResultByTurkishRepublicIdNo(String turkishRepublicIdNo)
     this.id = id;
        this.creditLimit = creditLimit;
        this.percentageOfGuarantee = percentageOfGuarantee;
        this.creditLimitMultiplierCoefficient = creditLimitMultiplierCoefficient;
        this.creditScoreLowerLimit = creditScoreLowerLimit;
        this.creditScoreUpperLimit = creditScoreUpperLimit;
        this.salaryLowerLimit = salaryLowerLimit;
        this.salaryUpperLimit = salaryUpperLimit;
 */