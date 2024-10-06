package org.spacolino.taxationservice.service;

import java.math.BigDecimal;

public interface TaxationStrategy {
    TaxationResult calculateTax(BigDecimal playedAmount, BigDecimal possibleReturnAmount);
}
