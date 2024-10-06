package org.spacolino.taxationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TaxationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int traderId;
    private BigDecimal playedAmount;
    private BigDecimal odd;
    private BigDecimal possibleReturnAmount;
    private BigDecimal taxAmount;
    private BigDecimal taxRate;
    private LocalDateTime calculationTime;

    public TaxationEntity() {}

    public TaxationEntity(int traderId, BigDecimal playedAmount, BigDecimal odd,
                          BigDecimal possibleReturnAmount, BigDecimal taxAmount,
                          BigDecimal taxRate, LocalDateTime calculationTime) {
        this.traderId = traderId;
        this.playedAmount = playedAmount;
        this.odd = odd;
        this.possibleReturnAmount = possibleReturnAmount;
        this.taxAmount = taxAmount;
        this.taxRate = taxRate;
        this.calculationTime = calculationTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public BigDecimal getPlayedAmount() {
        return playedAmount;
    }

    public void setPlayedAmount(BigDecimal playedAmount) {
        this.playedAmount = playedAmount;
    }

    public BigDecimal getOdd() {
        return odd;
    }

    public void setOdd(BigDecimal odd) {
        this.odd = odd;
    }

    public BigDecimal getPossibleReturnAmount() {
        return possibleReturnAmount;
    }

    public void setPossibleReturnAmount(BigDecimal possibleReturnAmount) {
        this.possibleReturnAmount = possibleReturnAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public LocalDateTime getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(LocalDateTime calculationTime) {
        this.calculationTime = calculationTime;
    }
}
