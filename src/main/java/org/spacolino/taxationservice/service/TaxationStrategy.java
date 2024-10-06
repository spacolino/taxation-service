package org.spacolino.taxationservice.service;

import org.spacolino.taxationservice.model.TaxationResult;

import java.math.BigDecimal;

public interface TaxationStrategy {
    TaxationResult calculateTax(BigDecimal playedAmount, BigDecimal possibleReturnAmount);
}
