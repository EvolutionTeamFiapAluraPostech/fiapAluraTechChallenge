package br.com.nursingcalculator.calculation.model.service;

import static br.com.nursingcalculator.calculation.model.message.CalculationMessages.CALCULATION_ID_NOT_FOUND;

import br.com.nursingcalculator.calculation.infrastructure.repository.CalculationRepository;
import br.com.nursingcalculator.calculation.model.entity.Calculation;
import jakarta.persistence.NoResultException;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

  private final CalculationRepository calculationRepository;

  public CalculationService(CalculationRepository calculationRepository) {
    this.calculationRepository = calculationRepository;
  }

  public Calculation findCalculationRequiredById(UUID uuid) {
    return calculationRepository.findById(uuid)
        .orElseThrow(() -> new NoResultException(CALCULATION_ID_NOT_FOUND.formatted(uuid)));
  }

  public Page<Calculation> getAllCalculationsPaginated(Pageable pageable) {
    return calculationRepository.findAll(pageable);
  }
}
