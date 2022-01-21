package com.asimkilic.loan.application.service.entityservice.customer;

import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
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
        return getRepository().findCustomerByTurkishRepublicIdNoAndStatus(turkishRepublicIdNo, EnumCustomerStatus.ACTIVE);
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return getRepository().findCustomerByEmailAndStatus(email, EnumCustomerStatus.ACTIVE);
    }

    public Optional<Customer> findCustomerByTurkishRepublicIdNoAndDateOfBirth(String turkishRepublicIdentityNo, Date dateOfBirth) {
        return getRepository().findCustomerByTurkishRepublicIdNoAndDateOfBirthAndStatus(turkishRepublicIdentityNo, dateOfBirth, EnumCustomerStatus.ACTIVE);
    }

    public boolean existsCustomerByTurkishRepublicIdNo(String turkishRepublicIdNo) {
        return getRepository().existsCustomerByTurkishRepublicIdNoAndStatus(turkishRepublicIdNo, EnumCustomerStatus.ACTIVE);
    }

    public boolean existsCustomerByEmail(String email) {
        return getRepository().existsCustomerByEmailAndStatus(email, EnumCustomerStatus.ACTIVE);
    }
    public boolean existsActiveCustomerById(String id) {
        return getRepository().existsCustomerByIdAndStatus(id, EnumCustomerStatus.ACTIVE);
    }

    public boolean existsCustomerByPrimaryPhone(String primaryPhone) {
        return getRepository().existsCustomerByPrimaryPhoneAndStatus(primaryPhone, EnumCustomerStatus.ACTIVE);
    }

    public boolean validateUpdateCustomerCredentialsNotInUse(String customerId, String turkishRepublicIdNo, String email, String primaryPhone) {
        return getRepository().validateUpdateCustomerCredentialsNotInUse(customerId, turkishRepublicIdNo, email, primaryPhone);
    }
    public boolean validateNewCustomerCredentialsNotInUse(String turkishRepublicIdNo, String email, String primaryPhone) {
        return getRepository().validateNewCustomerCredentialsNotInUse(turkishRepublicIdNo, email, primaryPhone);
    }
}
