package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.entity.PhoneBook;
import com.asimkilic.loan.application.service.entityservice.customer.PhoneBookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneBookService {
    private final PhoneBookEntityService phoneBookEntityService;

    protected boolean existsPhoneBookByPhone(String phone) {
        return phoneBookEntityService.existsPhoneBookByPhone(phone);
    }
    protected List<PhoneBook> savePhoneList(List<PhoneBook> phoneBookList){
        return phoneBookEntityService.saveAll(phoneBookList);
    }
}
