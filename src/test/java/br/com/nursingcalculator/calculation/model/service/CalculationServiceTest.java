package br.com.nursingcalculator.calculation.model.service;

import static br.com.nursingcalculator.shared.testData.hospital.CalculationTestData.createNewCalculation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.nursingcalculator.calculation.infrastructure.repository.CalculationRepository;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

  @Mock
  private CalculationRepository calculationRepository;
  @InjectMocks
  private CalculationService calculationService;

  @Test
  void shouldFindDepartmentRequiredByIdWhenDepartmentIdExists() {
    var calculation = createNewCalculation();
    when(calculationRepository.findById(calculation.getId())).thenReturn(Optional.of(calculation));

    var calculationFound = calculationService.findCalculationRequiredById(calculation.getId());

    assertThat(calculationFound).isNotNull();
    assertThat(calculationFound.getDescription()).isEqualTo(calculation.getDescription());
  }

  @Test
  void shouldReturnEmptyWhenFindDepartmentRequiredByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(calculationRepository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(NoResultException.class,
        () -> calculationService.findCalculationRequiredById(uuid));
  }
}