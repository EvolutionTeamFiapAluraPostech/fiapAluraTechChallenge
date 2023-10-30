package br.com.nursingcalculator.calculation.application.usecase;

import br.com.nursingcalculator.calculation.model.entity.CalculationState;
import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import br.com.nursingcalculator.calculation.model.service.CalculationService;
import br.com.nursingcalculator.hospital.model.service.DepartmentService;
import br.com.nursingcalculator.hospital.model.service.HospitalService;
import br.com.nursingcalculator.profession.model.service.ProfessionService;
import org.springframework.stereotype.Service;

@Service
public class GetStaffingBoardForTestingUseCase {

  private final HospitalService hospitalService;
  private final DepartmentService departmentService;
  private final ProfessionService professionService;
  private final CalculationService calculationService;

  public GetStaffingBoardForTestingUseCase(
      HospitalService hospitalService, DepartmentService departmentService,
      ProfessionService professionService, CalculationService calculationService) {
    this.hospitalService = hospitalService;
    this.departmentService = departmentService;
    this.professionService = professionService;
    this.calculationService = calculationService;
  }

  public StaffingBoard execute(Integer daysOfWeek, Integer weeklyWorkLoad) {
    var hospital = hospitalService.findTheLastHospitalSaved();
    var department = departmentService.findTheLastDepartmentSaved();
    var profession = professionService.findProfessionByDescription("ENFERMEIRO(A)");
    var calculation = calculationService.findTheLastCalculationSaved();
    return StaffingBoard.builder()
        .calculationState(CalculationState.ABERTO)
        .hospital(hospital)
        .department(department)
        .profession(profession)
        .calculation(calculation)
        .daysPerWeek(daysOfWeek)
        .weeklyWorkLoad(weeklyWorkLoad)
        .build();
  }
}
