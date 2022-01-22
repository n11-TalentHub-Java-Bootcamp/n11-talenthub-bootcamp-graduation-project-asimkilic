package com.asimkilic.loan.application.configuration;

import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.repository.creditconstraint.CreditConstraintRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreditConstraintDataInitializerApplication implements CommandLineRunner {
    private final CreditConstraintRepository creditConstraintRepository;

    public CreditConstraintDataInitializerApplication(CreditConstraintRepository creditConstraintRepository) {
        this.creditConstraintRepository = creditConstraintRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeCreditConstraintsData();
    }

    private void initializeCreditConstraintsData() {
        if (creditConstraintRepository.count() == 0) {
            List<CreditConstraint> creditConstraintList = new ArrayList<>();
            CreditConstraint creditConstraint1 = CreditConstraint.builder()
                    .creditLimit(BigDecimal.valueOf(10000))
                    .creditLimitMultiplierCoefficient(BigDecimal.ZERO)
                    .creditScoreLowerLimit(BigDecimal.valueOf(500))
                    .creditScoreUpperLimit(BigDecimal.valueOf(1000))
                    .percentageOfGuarantee(BigDecimal.TEN)
                    .salaryLowerLimit(BigDecimal.ZERO)
                    .salaryUpperLimit(BigDecimal.valueOf(5000))
                    .build();
            creditConstraintList.add(creditConstraint1);
            CreditConstraint creditConstraint2 = CreditConstraint.builder()
                    .creditLimit(BigDecimal.valueOf(20000))
                    .creditLimitMultiplierCoefficient(BigDecimal.ZERO)
                    .creditScoreLowerLimit(BigDecimal.valueOf(500))
                    .creditScoreUpperLimit(BigDecimal.valueOf(1000))
                    .percentageOfGuarantee(BigDecimal.valueOf(20))
                    .salaryLowerLimit(BigDecimal.valueOf(5000))
                    .salaryUpperLimit(BigDecimal.valueOf(10000))
                    .build();
            creditConstraintList.add(creditConstraint2);
            CreditConstraint creditConstraint3 = CreditConstraint.builder()
                    .creditLimit(BigDecimal.valueOf(0))
                    .creditLimitMultiplierCoefficient(BigDecimal.valueOf(0.5))
                    .creditScoreLowerLimit(BigDecimal.valueOf(500))
                    .creditScoreUpperLimit(BigDecimal.valueOf(1000))
                    .percentageOfGuarantee(BigDecimal.valueOf(25))
                    .salaryLowerLimit(BigDecimal.valueOf(10000))
                    .build();
            creditConstraintList.add(creditConstraint3);
            CreditConstraint creditConstraint4 = CreditConstraint.builder()
                    .creditLimit(BigDecimal.ZERO)
                    .creditLimitMultiplierCoefficient(BigDecimal.ONE)
                    .creditScoreLowerLimit(BigDecimal.valueOf(1000))
                    .creditScoreUpperLimit(BigDecimal.valueOf(9999))
                    .percentageOfGuarantee(BigDecimal.valueOf(50))
                    .build();
            creditConstraintList.add(creditConstraint4);
            creditConstraintRepository.saveAll(creditConstraintList);
        }

    }
}
