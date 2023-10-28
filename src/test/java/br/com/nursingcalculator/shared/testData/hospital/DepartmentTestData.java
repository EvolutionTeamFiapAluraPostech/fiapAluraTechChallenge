package br.com.nursingcalculator.shared.testData.hospital;

import br.com.nursingcalculator.hospital.model.entity.Department;

public final class DepartmentTestData {

  public static final String DEFAULT_DEPARTMENT_NAME = "TEST UN ASSIST, APOIO, DIAG e TERAPÊUTICA";

  public static Department createNewDepartment() {
    return Department.builder()
        .name(DEFAULT_DEPARTMENT_NAME)
        .build();
  }
}
