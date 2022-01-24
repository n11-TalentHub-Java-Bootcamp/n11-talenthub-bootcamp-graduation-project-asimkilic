package com.asimkilic.loan.application.service.entityservice.credit;

import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.gen.service.entity.BaseEntityService;
import com.asimkilic.loan.application.repository.credit.CreditRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class CreditEntityService extends BaseEntityService<Credit, CreditRepository> {
    public CreditEntityService(CreditRepository repository) {
        super(repository);
    }

    public boolean doesCustomerHaveApprovedCreditByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return getRepository().existsCreditByCustomer_TurkishRepublicIdNoAndCreditStatus(turkishRepublicIdNo, EnumCreditStatus.APPROVED);
    }

    public CreditConstraint findSuitableCreditConstraint(BigDecimal customerSalary, BigDecimal creditScore) {

        return  getRepository().findSuitableCreditConstraint(customerSalary, creditScore);

    }
    public Optional<Credit> findCreditResultByTurkishRepublicIdNo(String turkishRepublicIdNo){
        return getRepository().findAllByCustomer_TurkishRepublicIdNoOrderByCreationTimeDesc(turkishRepublicIdNo);
    }
}
