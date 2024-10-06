package org.spacolino.taxationservice.model;

public record TaxationResponse(
        double possibleReturnAmount,
        double possibleReturnAmountBefTax,
        double possibleReturnAmountAfterTax,
        double taxRate,
        double taxAmount
) {}
