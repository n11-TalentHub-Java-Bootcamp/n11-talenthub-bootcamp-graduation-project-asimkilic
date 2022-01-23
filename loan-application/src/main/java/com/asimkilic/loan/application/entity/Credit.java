package com.asimkilic.loan.application.entity;


import com.asimkilic.loan.application.gen.entity.BaseEntity;
import com.asimkilic.loan.application.gen.enums.EnumCreditStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit implements Serializable, BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "credit_id", nullable = false, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_CREDIT_CUSTOMER_ID"), nullable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_constraint_id", foreignKey = @ForeignKey(name = "FK_CREDIT_CREDITCONSTRAINT_ID"), updatable = false)
    private CreditConstraint creditConstraint;

    @Column(name = "credit_limit",precision = 11, scale = 2, updatable = false)
    private BigDecimal creditLimit;

    @Column(name = "creation_time", updatable = false, nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "salary_at_the_time_of_aplication", precision = 11, scale = 2, updatable = false, nullable = false)
    private BigDecimal salaryAtTheTimeOfApplication;

    @Column(name = "amount_of_guarantee_at_the_time_of_aplication", precision = 11, scale = 2, updatable = false)
    private BigDecimal amountOfGuaranteeAtTheTimeOfApplication;

    @Column(name = "credit_score_at_the_time_of_aplication", updatable = false, nullable = false)
    private BigDecimal creditScoreAtTheTimeOfApplication;

    @Column(name = "credit_status", updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumCreditStatus creditStatus;


}