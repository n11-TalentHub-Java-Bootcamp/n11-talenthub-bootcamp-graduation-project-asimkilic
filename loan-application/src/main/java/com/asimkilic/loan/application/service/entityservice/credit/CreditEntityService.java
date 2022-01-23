package com.asimkilic.loan.application.service.entityservice.credit;

import com.asimkilic.loan.application.entity.Credit;
import com.asimkilic.loan.application.gen.service.BaseEntityService;
import com.asimkilic.loan.application.repository.credit.CreditRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditEntityService extends BaseEntityService<Credit, CreditRepository> {
    public CreditEntityService(CreditRepository repository) {
        super(repository);
    }
}
