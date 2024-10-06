package org.spacolino.taxationservice.repository;

import org.spacolino.taxationservice.model.TaxationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxationRepository extends JpaRepository<TaxationEntity, Long> {
}
