package org.spacolino.taxationservice.controller;

import org.spacolino.taxationservice.model.TaxationEntity;
import org.spacolino.taxationservice.model.TaxationRequest;
import org.spacolino.taxationservice.model.TaxationResponse;
import org.spacolino.taxationservice.repository.TaxationRepository;
import org.spacolino.taxationservice.service.GeneralTaxationStrategy;
import org.spacolino.taxationservice.model.TaxationResult;
import org.spacolino.taxationservice.service.TaxationStrategy;
import org.spacolino.taxationservice.service.WinningsTaxationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/taxation")
public class TaxationController {
    private final GeneralTaxationStrategy generalTaxationStrategy;
    private final WinningsTaxationStrategy winningsTaxationStrategy;
    private final TaxationRepository taxationRepository;

    @Autowired
    public TaxationController(GeneralTaxationStrategy generalTaxationStrategy,
                              WinningsTaxationStrategy winningsTaxationStrategy,
                              TaxationRepository taxationRepository) {
        this.generalTaxationStrategy = generalTaxationStrategy;
        this.winningsTaxationStrategy = winningsTaxationStrategy;
        this.taxationRepository = taxationRepository;
    }

    @PostMapping("/calculate")
    public TaxationResponse calculateTax(@RequestBody TaxationRequest request) {
        BigDecimal playedAmount = BigDecimal.valueOf(request.playedAmount());
        BigDecimal odd = BigDecimal.valueOf(request.odd());
        BigDecimal possibleReturnAmountBefTax = playedAmount.multiply(odd).setScale(2, RoundingMode.HALF_UP);

        TaxationStrategy taxationStrategy = getTaxationStrategy(request.traderId());
        TaxationResult taxationResult = taxationStrategy.calculateTax(playedAmount, possibleReturnAmountBefTax);

        // store taxation result
        TaxationEntity entity = new TaxationEntity(
                request.traderId(),
                playedAmount,
                odd,
                possibleReturnAmountBefTax,
                taxationResult.taxAmount(),
                taxationResult.taxRate(),
                LocalDateTime.now()
        );
        taxationRepository.save(entity);

        return new TaxationResponse(
                possibleReturnAmountBefTax.doubleValue(),
                possibleReturnAmountBefTax.doubleValue(),
                taxationResult.possibleReturnAmountAfterTax().doubleValue(),
                taxationResult.taxRate().doubleValue(),
                taxationResult.taxAmount().doubleValue()
        );
    }

    @GetMapping("/calculations")
    public List<TaxationEntity> getAllCalculations() {
        return taxationRepository.findAll();
    }

    @GetMapping("/calculations/{id}")
    public ResponseEntity<TaxationEntity> getCalculationById(@PathVariable Long id) {
        return taxationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private TaxationStrategy getTaxationStrategy(int traderId) {
        return traderId % 2 == 0 ? winningsTaxationStrategy : generalTaxationStrategy;
    }
}
