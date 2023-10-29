package br.com.nursingcalculator.shared.testData.hospital;

import static br.com.nursingcalculator.shared.testData.hospital.CalculationTestData.createNewCalculation;
import static br.com.nursingcalculator.shared.testData.hospital.DepartmentTestData.createNewDepartment;
import static br.com.nursingcalculator.shared.testData.hospital.HospitalTestData.createNewHospital;
import static br.com.nursingcalculator.shared.testData.hospital.ProfessionTestData.createNewProfession;

import br.com.nursingcalculator.calculation.model.entity.CalculationState;
import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;

public final class StaffingBoardTestData {

  public static StaffingBoard createNewStaffingBoard() {
    var hospital = createNewHospital();
    var department = createNewDepartment();
    var profession = createNewProfession();
    var calculation = createNewCalculation();

    return StaffingBoard.builder()
        .calculationState(CalculationState.ABERTO)
        .hospital(hospital)
        .department(department)
        .profession(profession)
        .calculation(calculation)
        .build();
  }
}
