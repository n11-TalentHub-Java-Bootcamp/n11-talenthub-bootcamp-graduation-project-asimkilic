package com.asimkilic.loan.application.entity;

import com.asimkilic.loan.application.generic.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable, BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", nullable = false, updatable = false)
    private String id;

    @Column(name = "turkish_republic_id_no", length = 11, unique = true)
    private String turkishRepublicIdNo;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "monthly_salary", precision = 11, scale = 2)
    private BigDecimal monthlySalary;

    @Column(name = "amount_of_guarantee", precision = 11, scale = 2)
    private BigDecimal amountOfGuarantee;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PhoneBook> phones = new HashSet<>();


}
