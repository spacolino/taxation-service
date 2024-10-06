package org.spacolino.taxationservice.controller;

import org.spacolino.taxationservice.model.TaxationRequest;
import org.spacolino.taxationservice.model.TaxationResponse;
import org.spacolino.taxationservice.service.GeneralTaxationStrategy;
import org.spacolino.taxationservice.service.TaxationResult;
import org.spacolino.taxationservice.service.TaxationStrategy;
import org.spacolino.taxationservice.service.WinningsTaxationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/api/taxation")
public class TaxationController {
    private final GeneralTaxationStrategy generalTaxationStrategy;
    private final WinningsTaxationStrategy winningsTaxationStrategy;

    @Autowired
    public TaxationController(GeneralTaxationStrategy generalTaxationStrategy, WinningsTaxationStrategy winningsTaxationStrategy) {
        this.generalTaxationStrategy = generalTaxationStrategy;
        this.winningsTaxationStrategy = winningsTaxationStrategy;
    }

    @PostMapping("/calculate")
    public TaxationResponse calculateTax(@RequestBody TaxationRequest request) {
        BigDecimal playedAmount = BigDecimal.valueOf(request.playedAmount());
        BigDecimal odd = BigDecimal.valueOf(request.odd());
        BigDecimal possibleReturnAmountBefTax = playedAmount.multiply(odd).setScale(2, RoundingMode.HALF_UP);

        TaxationStrategy taxationStrategy = getTaxationStrategy(request.traderId());
        TaxationResult taxationResult = taxationStrategy.calculateTax(playedAmount, possibleReturnAmountBefTax);

        return new TaxationResponse(
                possibleReturnAmountBefTax.doubleValue(),
                possibleReturnAmountBefTax.doubleValue(),
                taxationResult.possibleReturnAmountAfterTax().doubleValue(),
                taxationResult.taxRate().doubleValue(),
                taxationResult.taxAmount().doubleValue()
        );
    }

    private TaxationStrategy getTaxationStrategy(int traderId) {
        return traderId % 2 == 0 ? winningsTaxationStrategy : generalTaxationStrategy;
    }
}
