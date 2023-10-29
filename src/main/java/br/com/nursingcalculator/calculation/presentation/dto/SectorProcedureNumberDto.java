package br.com.nursingcalculator.calculation.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record SectorProcedureNumberDto(
    @NotNull(message = "Sector is required.")
    String sectorId,
    @NotNull(message = "Quantity of procedures is required.")
    @Positive(message = "Quantity of procedures must be greater then zero")
    Integer quantitiesOfProcedures,
    BigDecimal totalHours
    ) {

}
