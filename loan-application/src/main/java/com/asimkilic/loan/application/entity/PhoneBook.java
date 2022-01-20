package com.asimkilic.loan.application.entity;

import com.asimkilic.loan.application.generic.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="phone_book")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneBook implements Serializable, BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "phone_book_id", nullable = false, updatable = false)
    private String id;

    @Column(name="phone",length = 10,unique = true, nullable = false)
    private String phone;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="customer_id", foreignKey = @ForeignKey(name="FK_PHONEBOOK_CUSTOMER_ID"),nullable = false)
    private Customer customer;

}
