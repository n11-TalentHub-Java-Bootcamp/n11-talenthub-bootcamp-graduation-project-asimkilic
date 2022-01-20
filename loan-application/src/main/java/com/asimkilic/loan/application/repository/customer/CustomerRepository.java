package com.asimkilic.loan.application.repository.customer;

import com.asimkilic.loan.application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo);

    boolean existsCustomerByEmail(String email);

    boolean existsCustomerById(String id);
}
