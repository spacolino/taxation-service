package org.spacolino.taxationservice.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class WinningsTaxationStrategy implements TaxationStrategy{

    @Override
    public TaxationResult calculateTax(BigDecimal playedAmount, BigDecimal possibleReturnAmount) {
        BigDecimal winnings = possibleReturnAmount.subtract(playedAmount);
        BigDecimal taxRate = new BigDecimal("0.10");
        BigDecimal taxAmount = winnings.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal possibleReturnAmountAfterTax = possibleReturnAmount.subtract(taxAmount);

        return new TaxationResult(possibleReturnAmountAfterTax, taxRate, taxAmount);
    }
}
