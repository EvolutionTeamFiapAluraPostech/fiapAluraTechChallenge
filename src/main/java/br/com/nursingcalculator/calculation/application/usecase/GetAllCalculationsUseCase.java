package br.com.nursingcalculator.calculation.application.usecase;

import br.com.nursingcalculator.calculation.model.entity.Calculation;
import br.com.nursingcalculator.calculation.model.service.CalculationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllCalculationsUseCase {

  private final CalculationService calculationService;

  public GetAllCalculationsUseCase(CalculationService calculationService) {
    this.calculationService = calculationService;
  }

  public Page<Calculation> execute(Pageable pageable) {
    return calculationService.getAllCalculationsPaginated(pageable);
  }
}
