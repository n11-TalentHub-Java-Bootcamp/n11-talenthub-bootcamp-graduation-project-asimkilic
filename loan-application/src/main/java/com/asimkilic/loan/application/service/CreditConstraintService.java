package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.service.entityservice.creditconstraint.CreditConstraintEntityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditConstraintService {
    private final CreditConstraintEntityService creditConstraintEntityService;


}
