package com.asimkilic.loan.application.entity;

import com.asimkilic.loan.application.gen.entity.BaseEntity;
import com.asimkilic.loan.application.gen.enums.EnumCustomerStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "customer")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable, BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_id", nullable = false, updatable = false)
    private String id;

    @Column(name = "turkish_republic_id_no", length = 11, unique = true, nullable = false, updatable = false)
    private String turkishRepublicIdNo;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "monthly_salary", precision = 11, scale = 2)
    private BigDecimal monthlySalary;

    @Column(name = "amount_of_guarantee", precision = 11, scale = 2)
    private BigDecimal amountOfGuarantee;

    @Column(name = "primary_phone", length = 10, unique = true, nullable = false)
    private String primaryPhone;

    @Column(name = "secondary_phone", length = 10)
    private String secondaryPhone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnumCustomerStatus status;

    @Column(name = "update_time", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateTime;

    @Column(name = "creation_time", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationTime;


}
