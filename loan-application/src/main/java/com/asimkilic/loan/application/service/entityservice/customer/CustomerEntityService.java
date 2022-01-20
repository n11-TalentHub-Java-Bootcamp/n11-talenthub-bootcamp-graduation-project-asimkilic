package com.asimkilic.loan.application.service.entityservice.customer;

import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.service.BaseEntityService;
import com.asimkilic.loan.application.repository.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerEntityService extends BaseEntityService<Customer, CustomerRepository> {
    public CustomerEntityService(CustomerRepository repository) {
        super(repository);
    }

    public Optional<Customer> findCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return getRepository().findCustomerByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return getRepository().findCustomerByEmail(email);
    }

    public Optional<Customer> findCustomerByTurkishRepublicIdNoAndDateOfBirth(String turkishRepublicIdentityNo, Date dateOfBirth) {
        return getRepository().findCustomerByTurkishRepublicIdNoAndDateOfBirth(turkishRepublicIdentityNo, dateOfBirth);
    }

    public boolean existsCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return getRepository().existsCustomerByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    public boolean existsCustomerByEmail(String email) {
        return getRepository().existsCustomerByEmail(email);
    }

    public boolean existsCustomerById(String id) {
        return getRepository().existsCustomerById(id);
    }

    public boolean existsCustomerByPrimaryPhone(String primaryPhone) {
        return getRepository().existsCustomerByPrimaryPhone(primaryPhone);
    }

    public boolean validateUpdateCustomerCredentialsNotInUse(String customerId, String turkishRepublicIdNo, String email, String primaryPhone) {
        return getRepository().validateUpdateCustomerCredentialsNotInUse(customerId, turkishRepublicIdNo, email, primaryPhone);
    }
}
