package br.com.nursingcalculator.shared.testData.hospital;

import br.com.nursingcalculator.calculation.model.entity.Calculation;

public final class CalculationTestData {

  public static final String DEFAULT_CALCULATION_DESCRIPTION = "TEST CÁLCULO UN ASSIST, APOIO, DIAG e TERAPÊUTICA";

  public static Calculation createNewCalculation() {
    return Calculation.builder()
        .description(DEFAULT_CALCULATION_DESCRIPTION)
        .build();
  }
}
