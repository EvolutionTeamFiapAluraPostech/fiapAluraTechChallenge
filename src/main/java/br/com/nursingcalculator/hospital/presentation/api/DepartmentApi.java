package br.com.nursingcalculator.hospital.presentation.api;

import br.com.nursingcalculator.hospital.application.usecase.GetAllDepartmentsUseCase;
import br.com.nursingcalculator.hospital.presentation.dto.DepartmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentApi {

  private final GetAllDepartmentsUseCase getAllDepartmentsUseCase;

  public DepartmentApi(GetAllDepartmentsUseCase getAllDepartmentsUseCase) {
    this.getAllDepartmentsUseCase = getAllDepartmentsUseCase;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<DepartmentDto> getAllDepartmentsPaginated(
      @PageableDefault(sort = {"name"}) Pageable pageable) {
    var departmentsPage = getAllDepartmentsUseCase.execute(pageable);
    return DepartmentDto.toPage(departmentsPage);
  }

}
