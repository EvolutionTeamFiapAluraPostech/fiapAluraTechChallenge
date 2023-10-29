package br.com.nursingcalculator.calculation.infrastructure.repository;

import br.com.nursingcalculator.calculation.model.entity.Calculation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, UUID> {

}
