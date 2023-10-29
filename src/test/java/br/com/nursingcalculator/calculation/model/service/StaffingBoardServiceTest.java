package br.com.nursingcalculator.calculation.model.service;

import static br.com.nursingcalculator.shared.testData.hospital.StaffingBoardTestData.createNewStaffingBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

import br.com.nursingcalculator.calculation.infrastructure.repository.StaffingBoardRepository;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StaffingBoardServiceTest {

  @Mock
  private StaffingBoardRepository staffingBoardRepository;
  @InjectMocks
  private StaffingBoardService staffingBoardService;

  @Test
  void shouldSaveHospitalWhenAllDepartmentAttributesAreCorrect() {
    var staffingBoard = createNewStaffingBoard();
    when(staffingBoardRepository.save(staffingBoard)).then(returnsFirstArg());

    var staffingBoardSaved = staffingBoardService.save(staffingBoard);

    assertThat(staffingBoardSaved).isNotNull();
    assertThat(staffingBoardSaved.getHospital()).isEqualTo(staffingBoard.getHospital());
    assertThat(staffingBoardSaved.getDepartment()).isEqualTo(staffingBoard.getDepartment());
    assertThat(staffingBoardSaved.getProfession()).isEqualTo(staffingBoard.getProfession());
    assertThat(staffingBoardSaved.getCalculation()).isEqualTo(staffingBoard.getCalculation());
  }

  @Test
  void shouldFindDepartmentByIdWhenDepartmentIdExists() {
    var staffingBoard = createNewStaffingBoard();
    when(staffingBoardRepository.findById(staffingBoard.getId())).thenReturn(
        Optional.of(staffingBoard));

    var staffingBoardFound = staffingBoardService.findStaffingBoardById(staffingBoard.getId())
        .orElse(null);

    assertThat(staffingBoardFound).isNotNull();
    assertThat(staffingBoardFound.getHospital()).isEqualTo(staffingBoard.getHospital());
    assertThat(staffingBoardFound.getDepartment()).isEqualTo(staffingBoard.getDepartment());
    assertThat(staffingBoardFound.getProfession()).isEqualTo(staffingBoard.getProfession());
    assertThat(staffingBoardFound.getCalculation()).isEqualTo(staffingBoard.getCalculation());
  }

  @Test
  void shouldReturnEmptyWhenFindDepartmentByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(staffingBoardRepository.findById(uuid)).thenReturn(Optional.empty());

    var staffingBoardFound = staffingBoardService.findStaffingBoardById(uuid);

    assertThat(staffingBoardFound.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  void shouldFindDepartmentRequiredByIdWhenDepartmentIdExists() {
    var staffingBoard = createNewStaffingBoard();
    when(staffingBoardRepository.findById(staffingBoard.getId())).thenReturn(
        Optional.of(staffingBoard));

    var staffingBoardFound = staffingBoardService.findStaffingBoardRequiredById(
        staffingBoard.getId());

    assertThat(staffingBoardFound).isNotNull();
    assertThat(staffingBoardFound.getHospital()).isEqualTo(staffingBoard.getHospital());
    assertThat(staffingBoardFound.getDepartment()).isEqualTo(staffingBoard.getDepartment());
    assertThat(staffingBoardFound.getProfession()).isEqualTo(staffingBoard.getProfession());
    assertThat(staffingBoardFound.getCalculation()).isEqualTo(staffingBoard.getCalculation());
  }

  @Test
  void shouldReturnEmptyWhenFindDepartmentRequiredByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(staffingBoardRepository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(NoResultException.class,
        () -> staffingBoardService.findStaffingBoardRequiredById(uuid));
  }
}