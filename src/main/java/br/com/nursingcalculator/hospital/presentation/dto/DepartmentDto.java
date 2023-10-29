package br.com.nursingcalculator.hospital.presentation.dto;

import br.com.nursingcalculator.hospital.model.entity.Department;
import org.springframework.data.domain.Page;

public record DepartmentDto(
    String id,
    String name
) {

  public DepartmentDto(Department department) {
    this(department.getId() != null ? department.getId().toString() : null,
        department.getName());
  }

  public static Page<DepartmentDto> toPage(Page<Department> departmentsPage) {
    return departmentsPage.map(DepartmentDto::new);
  }
}
