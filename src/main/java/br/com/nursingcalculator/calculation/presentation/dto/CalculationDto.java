package br.com.nursingcalculator.calculation.presentation.dto;

import br.com.nursingcalculator.calculation.model.entity.Calculation;
import org.springframework.data.domain.Page;

public record CalculationDto(
    String id,
    String description
) {

  public CalculationDto(Calculation calculation) {
    this(calculation.getId() != null ? calculation.getId().toString() : null,
        calculation.getDescription());
  }

  public static Page<CalculationDto> toPage(Page<Calculation> calculationPage) {
    return calculationPage.map(CalculationDto::new);
  }
}
