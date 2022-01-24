package com.asimkilic.loan.application.service.entityservice.creditconstraint;

import com.asimkilic.loan.application.entity.CreditConstraint;
import com.asimkilic.loan.application.gen.service.entity.BaseEntityService;
import com.asimkilic.loan.application.repository.creditconstraint.CreditConstraintRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditConstraintEntityService extends BaseEntityService<CreditConstraint, CreditConstraintRepository> {

    public CreditConstraintEntityService(CreditConstraintRepository repository) {
        super(repository);
    }

    @Override
    public CreditConstraint save(CreditConstraint entity) {
        if(entity.getId()!=null){
            // TODO: credit constraint güncellemesini kapat.
            //throw new CreditConstraintsCannotUpdatableException("easd");
            return null;
        }
        return super.save(entity);
    }
}
