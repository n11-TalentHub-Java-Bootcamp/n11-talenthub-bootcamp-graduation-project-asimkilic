package com.asimkilic.loan.application.service.entityservice.creditconstraint;

import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.gen.service.BaseEntityService;
import com.asimkilic.loan.application.repository.creditconstraint.CreditConstraintRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditConstraintEntityService extends BaseEntityService<CreditConstraint, CreditConstraintRepository> {

    public CreditConstraintEntityService(CreditConstraintRepository repository) {
        super(repository);
    }

}
