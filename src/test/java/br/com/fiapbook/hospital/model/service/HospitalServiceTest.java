package br.com.fiapbook.hospital.model.service;

import static br.com.fiapbook.shared.testData.hospital.HospitalTestData.createNewHospital;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

import br.com.fiapbook.hospital.infrastructure.repository.HospitalRepository;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HospitalServiceTest {

  @Mock
  private HospitalRepository hospitalRepository;
  @InjectMocks
  private HospitalService hospitalService;

  @Test
  void shouldSaveHospitalWhenAllHospitalAttributesAreCorrect() {
    var hospital = createNewHospital();
    when(hospitalRepository.save(hospital)).then(returnsFirstArg());

    var hospitalSaved = hospitalService.save(hospital);

    assertThat(hospitalSaved).isNotNull();
    assertThat(hospitalSaved.getName()).isEqualTo(hospital.getName());
    assertThat(hospitalSaved.getActive()).isEqualTo(hospital.getActive());
  }

  @Test
  void shouldFindHospitalByIdWhenHospitalIdExists() {
    var hospital = createNewHospital();
    when(hospitalRepository.findById(hospital.getId())).thenReturn(Optional.of(hospital));

    var hospitalFound = hospitalService.findHospitalById(hospital.getId()).orElse(null);

    assertThat(hospitalFound).isNotNull();
    assertThat(hospitalFound.getName()).isEqualTo(hospital.getName());
  }

  @Test
  void shouldReturnEmptyWhenFindHospitalByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(hospitalRepository.findById(uuid)).thenReturn(Optional.empty());

    var hospitalFound = hospitalService.findHospitalById(uuid);

    assertThat(hospitalFound.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  void shouldFindHospitalRequiredByIdWhenHospitalIdExists() {
    var hospital = createNewHospital();
    when(hospitalRepository.findById(hospital.getId())).thenReturn(Optional.of(hospital));

    var hospitalFound = hospitalService.findHospitalRequiredById(hospital.getId());

    assertThat(hospitalFound).isNotNull();
    assertThat(hospitalFound.getName()).isEqualTo(hospital.getName());
  }

  @Test
  void shouldReturnEmptyWhenFindHospitalRequiredByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(hospitalRepository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(NoResultException.class,
        () -> hospitalService.findHospitalRequiredById(uuid));
  }
}