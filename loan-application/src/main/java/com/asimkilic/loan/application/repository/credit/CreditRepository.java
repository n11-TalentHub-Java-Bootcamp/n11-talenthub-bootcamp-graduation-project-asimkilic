package com.asimkilic.loan.application.repository.credit;

import com.asimkilic.loan.application.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit,String> {
}
