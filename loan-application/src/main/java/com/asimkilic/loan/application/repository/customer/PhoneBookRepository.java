package com.asimkilic.loan.application.repository.customer;

import com.asimkilic.loan.application.entity.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, String> {

    boolean existsPhoneBookByPhone(String phone);
}
