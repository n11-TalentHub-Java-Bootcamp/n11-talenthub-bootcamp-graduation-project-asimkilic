package com.asimkilic.loan.application.repository.customer;

import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {


    List<Customer> findAllByStatus(EnumCustomerStatus status);
    boolean existsCustomerByTurkishRepublicIdNoAndStatus(String turkishRepublicIdNo,EnumCustomerStatus status);

    boolean existsCustomerByEmailAndStatus(String email,EnumCustomerStatus status);

    boolean existsCustomerByIdAndStatus(String id, EnumCustomerStatus status);

    boolean existsCustomerByPrimaryPhoneAndStatus(String primaryPhone,EnumCustomerStatus status);

    Optional<Customer> findCustomerByTurkishRepublicIdNoAndStatus(String turkishRepublicIdNo,EnumCustomerStatus status);

    Optional<Customer> findCustomerByEmailAndStatus(String email,EnumCustomerStatus status);

    Optional<Customer> findCustomerByTurkishRepublicIdNoAndDateOfBirthAndStatus(String turkishRepublicIdNo, Date dateOfBirth,EnumCustomerStatus status);


    @Query(value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END AS BOOL) " +
            " FROM customer " +
            " WHERE customer_id <> ?1 AND " +
            " (" +
            " email = ?2  )"
            , nativeQuery = true)
    boolean validateUpdateCustomerEmailCredentialNotInUse(String customerId, String email);


    @Query(value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END AS BOOL) " +
            " FROM customer " +
            " WHERE customer_id <> ?1 AND " +
            " ( primary_phone = ?2 )"
            , nativeQuery = true)
    boolean validateUpdateCustomerPrimaryPhoneCredentialNotInUse(String customerId, String primaryPhone);



 /*   @Query(value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END AS BOOL) " +
            " FROM customer " +
            " WHERE " +
            " (turkish_republic_id_no = ?1 OR " +
            " email = ?2 OR " +
            " primary_phone = ?3)"
            , nativeQuery = true)
    boolean validateNewCustomerCredentialsNotInUse(String turkishRepublicIdNo, String email, String primaryPhone);

*/


}
