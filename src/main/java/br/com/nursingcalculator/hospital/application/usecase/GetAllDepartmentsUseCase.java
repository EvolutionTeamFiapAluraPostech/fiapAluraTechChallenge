package br.com.nursingcalculator.hospital.application.usecase;

import br.com.nursingcalculator.hospital.model.entity.Department;
import br.com.nursingcalculator.hospital.model.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllDepartmentsUseCase {

  private final DepartmentService departmentService;

  public GetAllDepartmentsUseCase(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  public Page<Department> execute(Pageable pageable) {
    return departmentService.getAllDepartmentsPaginated(pageable);
  }
}
