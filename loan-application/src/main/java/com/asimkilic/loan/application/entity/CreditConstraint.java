package com.asimkilic.loan.application.entity;

import com.asimkilic.loan.application.gen.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="credit_constraint")
@Data
@ToString
@NoArgsConstructor
@Builder
public class  CreditConstraint implements Serializable, BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "credit_constraint_id", nullable = false, updatable = false)
    private String id;

    @Column(name="credit_limit",nullable = false)
    private BigDecimal creditLimit;

    @Column(name="percentage_of_guarantee",precision = 5,scale = 2,nullable = false)
    private BigDecimal percentageOfGuarantee;

    @Column(name="credit_limit_multiplier_coefficient",nullable = false,precision = 5,scale = 2)
    private BigDecimal creditLimitMultiplierCoefficient;

    @Column(name="credit_score_lower_limit",nullable = false)
    private BigDecimal creditScoreLowerLimit;

    @Column(name="credit_score_upper_limit",nullable = false)
    private BigDecimal creditScoreUpperLimit;

    @Column(name="salary_lower_limit")
    private BigDecimal salaryLowerLimit;

    @Column(name="salary_upper_limit")
    private BigDecimal salaryUpperLimit;

    public CreditConstraint(String id, BigDecimal creditLimit, BigDecimal percentageOfGuarantee, BigDecimal creditLimitMultiplierCoefficient, BigDecimal creditScoreLowerLimit, BigDecimal creditScoreUpperLimit, BigDecimal salaryLowerLimit, BigDecimal salaryUpperLimit) {
        this.id = id;
        this.creditLimit = creditLimit;
        this.percentageOfGuarantee = percentageOfGuarantee;
        this.creditLimitMultiplierCoefficient = creditLimitMultiplierCoefficient;
        this.creditScoreLowerLimit = creditScoreLowerLimit;
        this.creditScoreUpperLimit = creditScoreUpperLimit;
        this.salaryLowerLimit = salaryLowerLimit;
        this.salaryUpperLimit = salaryUpperLimit;
    }
}
