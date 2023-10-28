package br.com.fiapbook.hospital.model.service;

import static br.com.fiapbook.shared.testData.hospital.DepartmentTestData.createNewDepartment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

import br.com.fiapbook.hospital.infrastructure.repository.DepartmentRepository;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;
  @InjectMocks
  private DepartmentService departmentService;

  @Test
  void shouldSaveHospitalWhenAllDepartmentAttributesAreCorrect() {
    var department = createNewDepartment();
    when(departmentRepository.save(department)).then(returnsFirstArg());

    var departmentSaved = departmentService.save(department);

    assertThat(departmentSaved).isNotNull();
    assertThat(departmentSaved.getName()).isEqualTo(department.getName());
    assertThat(departmentSaved.getActive()).isEqualTo(department.getActive());
  }

  @Test
  void shouldFindDepartmentByIdWhenDepartmentIdExists() {
    var department = createNewDepartment();
    when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

    var departmentFound = departmentService.findDepartmentById(department.getId()).orElse(null);

    assertThat(departmentFound).isNotNull();
    assertThat(departmentFound.getName()).isEqualTo(department.getName());
  }

  @Test
  void shouldReturnEmptyWhenFindDepartmentByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(departmentRepository.findById(uuid)).thenReturn(Optional.empty());

    var departmentFound = departmentService.findDepartmentById(uuid);

    assertThat(departmentFound.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  void shouldFindDepartmentRequiredByIdWhenDepartmentIdExists() {
    var department = createNewDepartment();
    when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

    var departmentFound = departmentService.findDepartmentRequiredById(department.getId());

    assertThat(departmentFound).isNotNull();
    assertThat(departmentFound.getName()).isEqualTo(department.getName());
  }

  @Test
  void shouldReturnEmptyWhenFindDepartmentRequiredByIdDoesNotExist() {
    var uuid = UUID.randomUUID();
    when(departmentRepository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(NoResultException.class,
        () -> departmentService.findDepartmentRequiredById(uuid));
  }
}