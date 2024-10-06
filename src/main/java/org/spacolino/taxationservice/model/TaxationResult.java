package org.spacolino.taxationservice.model;

import java.math.BigDecimal;

public record TaxationResult(
        BigDecimal possibleReturnAmountAfterTax,
        BigDecimal taxRate,
        BigDecimal taxAmount
) {
}
