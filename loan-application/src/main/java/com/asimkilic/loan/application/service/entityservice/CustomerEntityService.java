package com.asimkilic.loan.application.service.entityservice;

import com.asimkilic.loan.application.entity.Customer;
import com.asimkilic.loan.application.generic.service.BaseEntityService;
import com.asimkilic.loan.application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerEntityService extends BaseEntityService<Customer, CustomerRepository> {
    public CustomerEntityService(CustomerRepository repository) {
        super(repository);
    }


}
