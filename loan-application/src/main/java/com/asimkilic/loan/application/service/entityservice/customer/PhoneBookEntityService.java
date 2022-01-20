package com.asimkilic.loan.application.service.entityservice.customer;

import com.asimkilic.loan.application.entity.PhoneBook;
import com.asimkilic.loan.application.generic.service.BaseEntityService;
import com.asimkilic.loan.application.repository.customer.PhoneBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneBookEntityService extends BaseEntityService<PhoneBook, PhoneBookRepository> {
    public PhoneBookEntityService(PhoneBookRepository repository) {
        super(repository);
    }

    public boolean existsPhoneBookByPhone(String phone) {
        return getRepository().existsPhoneBookByPhone(phone);
    }
    public List<PhoneBook> saveAll(List<PhoneBook> phoneBookList){
      return getRepository().saveAll(phoneBookList);
    }
}
