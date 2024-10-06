package org.spacolino.taxationservice.model;

public record TaxationRequest(
        int traderId,
        double playedAmount,
        double odd
) {}
