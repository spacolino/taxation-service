package org.spacolino.taxationservice.service;

import org.spacolino.taxationservice.model.TaxationResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class GeneralTaxationStrategy implements TaxationStrategy{
    @Override
    public TaxationResult calculateTax(BigDecimal playedAmount, BigDecimal possibleReturnAmount) {
        BigDecimal taxRate = new BigDecimal("0.10");
        BigDecimal taxAmount = possibleReturnAmount.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal possibleReturnAmountAfterTax = possibleReturnAmount.subtract(taxAmount);

        return new TaxationResult(possibleReturnAmountAfterTax, taxRate, taxAmount);
    }
}
