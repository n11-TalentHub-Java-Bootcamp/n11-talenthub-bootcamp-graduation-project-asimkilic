package com.asimkilic.loan.application.service;

import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import com.asimkilic.loan.application.gen.service.BaseCreditCalculationService;
import com.asimkilic.loan.application.gen.service.BaseCreditScoreInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final BaseCreditScoreInquiryService creditScoreService;
    private final BaseCreditCalculationService creditCalculator;

    public EnumCreditStatus applyCredit() {
        // TODO :
        // KRedi skoru sorgula
        // < 500 db kaydet sms at retun et gönder
        // kredi hesaplamasına gönder
        // gelen sonucu db'ye kaydet
        // sms ile bilgilendir
        // end pointe onay durum ve limit bilgisi dön

        return EnumCreditStatus.DENIED;
    }
}
/*
Kredinin neticelenmesi sonucunda ilgili başvuru veritabanına kaydedilir. Daha sonrasında
ise ilgili telefon numarasına bilgilendirme SMS’i gönderilir ve endpoint’ten onay durum
bilgisi (red veya onay), limit bilgisi dönülür.

Gerçekleştirilmiş bir kredi başvurusu sadece kimlik numarası ve doğum tarihi bilgisi ile
sorgulanabilir. Doğum tarihi ve kimlik bilgisi eşleşmezse sorgulanamamalıdır.
 */