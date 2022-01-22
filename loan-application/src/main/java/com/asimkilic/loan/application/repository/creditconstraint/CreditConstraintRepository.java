package com.asimkilic.loan.application.repository.creditconstraint;

import com.asimkilic.loan.application.entity.CreditConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditConstraintRepository extends JpaRepository<CreditConstraint,String> {
}
