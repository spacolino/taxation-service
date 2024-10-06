package org.spacolino.taxationservice.service;

import java.math.BigDecimal;

public record TaxationResult(
        BigDecimal possibleReturnAmountAfterTax,
        BigDecimal taxRate,
        BigDecimal taxAmount
) {
}
