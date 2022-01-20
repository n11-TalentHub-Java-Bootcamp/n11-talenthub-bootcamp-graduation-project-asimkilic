package com.asimkilic.loan.application.service.entityservice;

import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.generic.service.BaseEntityService;
import com.asimkilic.loan.application.repository.customer.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerEntityService extends BaseEntityService<Customer, CustomerRepository> {
    public CustomerEntityService(CustomerRepository repository) {
        super(repository);
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

}
