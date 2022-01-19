package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerEntityService customerEntityService;
}
