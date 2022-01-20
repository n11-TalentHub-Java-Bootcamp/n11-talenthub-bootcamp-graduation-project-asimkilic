package com.asimkilic.loan.application.repository.customer;

import com.asimkilic.loan.application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo);

    boolean existsCustomerByEmail(String email);

    boolean existsCustomerById(String id);

    boolean existsCustomerByPrimaryPhone(String primaryPhone);

    Optional<Customer> findCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo);

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerByTurkishRepublicIdNoAndDateOfBirth(String turkishRepublicIdNo, Date dateOfBirth);

    @Query(value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END AS BOOL) " +
            " FROM customer " +
            " WHERE customer_id <> ?1 AND " +
            " (turkish_republic_id_no = ?2 OR " +
            " email = ?3 OR " +
            " primary_phone = ?4)"
            , nativeQuery = true)
    boolean validateUpdateCustomerCredentialsNotInUse(String customerId, String turkishRepublicIdNo, String email, String primaryPhone);
}
