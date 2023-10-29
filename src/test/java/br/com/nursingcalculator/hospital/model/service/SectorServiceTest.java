package br.com.nursingcalculator.hospital.model.service;

import static br.com.nursingcalculator.shared.testData.hospital.SectorTestData.createNewSector;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

import br.com.nursingcalculator.hospital.infrastructure.repository.SectorRepository;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SectorServiceTest {

  @Mock
  private SectorRepository sectorRepository;
  @InjectMocks
  private SectorService sectorService;

  @Test
  void shouldSaveHospitalWhenAllDepartmentAttributesAreCorrect() {
    var sector = createNewSector();
    when(sectorRepository.save(sector)).then(returnsFirstArg());

    var sectorSaved = sectorService.save(sector);

    assertThat(sectorSaved).isNotNull();
    assertThat(sectorSaved.getDescription()).isEqualTo(sector.getDescription());
    assertThat(sectorSaved.getActive()).isEqualTo(sector.getActive());
  }

  @Test
  void shouldFindDepartmentByIdWhenDepartmentIdExists() {
    var sector = createNewSector();
    when(sectorRepository.findById(sector.getId())).thenReturn(Optional.of(sector));

    var sectorFound = sectorService.findSectorById(sector.getId()).orElse(null);

    assertThat(sectorFound).isNotNull();
    assertThat(sectorFound.getDescription()).isEqualTo(sector.getDescription());
  }

  @Test
  void shouldReturnEmptyWhenFindDepartmentByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(sectorRepository.findById(uuid)).thenReturn(Optional.empty());

    var sectorFound = sectorService.findSectorById(uuid);

    assertThat(sectorFound.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  void shouldFindDepartmentRequiredByIdWhenDepartmentIdExists() {
    var sector = createNewSector();
    when(sectorRepository.findById(sector.getId())).thenReturn(Optional.of(sector));

    var sectorFound = sectorService.findSectorRequiredById(sector.getId());

    assertThat(sectorFound).isNotNull();
    assertThat(sectorFound.getDescription()).isEqualTo(sector.getDescription());
  }

  @Test
  void shouldReturnEmptyWhenFindDepartmentRequiredByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(sectorRepository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(NoResultException.class,
        () -> sectorService.findSectorRequiredById(uuid));
  }
}