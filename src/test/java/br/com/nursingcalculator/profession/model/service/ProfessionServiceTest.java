package br.com.nursingcalculator.profession.model.service;

import static br.com.nursingcalculator.shared.testData.hospital.ProfessionTestData.createNewProfession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

import br.com.nursingcalculator.profession.infrastructure.repository.ProfessionRepository;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProfessionServiceTest {

  @Mock
  private ProfessionRepository professionRepository;
  @InjectMocks
  private ProfessionService professionService;

  @Test
  void shouldSaveHospitalWhenAllHospitalAttributesAreCorrect() {
    var profession = createNewProfession();
    when(professionRepository.save(profession)).then(returnsFirstArg());

    var professionSaved = professionService.save(profession);

    assertThat(professionSaved).isNotNull();
    assertThat(professionSaved.getDescription()).isEqualTo(profession.getDescription());
    assertThat(professionSaved.getActive()).isEqualTo(profession.getActive());
  }

  @Test
  void shouldFindHospitalByIdWhenHospitalIdExists() {
    var profession = createNewProfession();
    when(professionRepository.findById(profession.getId())).thenReturn(Optional.of(profession));

    var professionFound = professionService.findProfessionById(profession.getId()).orElse(null);

    assertThat(professionFound).isNotNull();
    assertThat(professionFound.getDescription()).isEqualTo(profession.getDescription());
  }

  @Test
  void shouldReturnEmptyWhenFindHospitalByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(professionRepository.findById(uuid)).thenReturn(Optional.empty());

    var professionFound = professionService.findProfessionById(uuid);

    assertThat(professionFound.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  void shouldFindHospitalRequiredByIdWhenHospitalIdExists() {
    var profession = createNewProfession();
    when(professionRepository.findById(profession.getId())).thenReturn(Optional.of(profession));

    var professionFound = professionService.findProfessionRequiredById(profession.getId());

    assertThat(professionFound).isNotNull();
    assertThat(professionFound.getDescription()).isEqualTo(profession.getDescription());
  }

  @Test
  void shouldReturnEmptyWhenFindHospitalRequiredByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(professionRepository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(NoResultException.class,
        () -> professionService.findProfessionRequiredById(uuid));
  }
}