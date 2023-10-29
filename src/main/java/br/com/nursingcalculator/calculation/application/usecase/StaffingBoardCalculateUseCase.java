package br.com.nursingcalculator.calculation.application.usecase;

import br.com.nursingcalculator.calculation.model.entity.CalculationState;
import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import br.com.nursingcalculator.calculation.model.service.CalculationService;
import br.com.nursingcalculator.calculation.model.service.StaffingBoardService;
import br.com.nursingcalculator.hospital.model.entity.SectorProfessions;
import br.com.nursingcalculator.hospital.model.service.DepartmentService;
import br.com.nursingcalculator.hospital.model.service.HospitalService;
import br.com.nursingcalculator.profession.model.service.ProfessionService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StaffingBoardCalculateUseCase {

  private static final BigDecimal TECHNICAL_SECURITY_INDEX = new BigDecimal("1.15").add(
      BigDecimal.ONE);

  private final HospitalService hospitalService;
  private final DepartmentService departmentService;
  private final ProfessionService professionService;
  private final CalculationService calculationService;
  private final StaffingBoardService staffingBoardService;

  public StaffingBoardCalculateUseCase(HospitalService hospitalService,
      DepartmentService departmentService, ProfessionService professionService,
      CalculationService calculationService, StaffingBoardService staffingBoardService) {
    this.hospitalService = hospitalService;
    this.departmentService = departmentService;
    this.professionService = professionService;
    this.calculationService = calculationService;
    this.staffingBoardService = staffingBoardService;
  }

  @Transactional
  public StaffingBoard execute(StaffingBoard staffingBoard,
      Map<SectorProfessions, Integer> sectorProfessionsQuantities) {
    staffingBoard = setAttributesInStaffingBoard(staffingBoard);
    calculateNumberOfCollaborators(staffingBoard, sectorProfessionsQuantities);
    return staffingBoardService.save(staffingBoard);
  }

  private StaffingBoard setAttributesInStaffingBoard(StaffingBoard staffingBoard) {
    var staffingBoardFound = checkIfIsNewCalculate(staffingBoard);

    var hospital = hospitalService.findHospitalRequiredById(staffingBoard.getHospital().getId());
    var department = departmentService.findDepartmentRequiredById(
        staffingBoard.getDepartment().getId());
    var profession = professionService.findProfessionRequiredById(
        staffingBoard.getProfession().getId());
    var calculation = calculationService.findCalculationRequiredById(
        staffingBoard.getCalculation().getId());

    if (staffingBoardFound == null) {
      staffingBoard.setHospital(hospital);
      staffingBoard.setDepartment(department);
      staffingBoard.setProfession(profession);
      staffingBoard.setCalculation(calculation);
      staffingBoard.setCalculationState(CalculationState.ABERTO);
      return staffingBoard;
    } else {
      staffingBoardFound.setHospital(hospital);
      staffingBoardFound.setDepartment(department);
      staffingBoardFound.setProfession(profession);
      staffingBoardFound.setCalculation(calculation);
      staffingBoardFound.setCalculationState(CalculationState.ANDAMENTO);
      return staffingBoardFound;
    }
  }

  private StaffingBoard checkIfIsNewCalculate(StaffingBoard staffingBoard) {
    if (staffingBoard.getId() != null) {
      return staffingBoardService.findStaffingBoardById(staffingBoard.getId())
          .orElse(null);
    }
    return null;
  }

  private void calculateNumberOfCollaborators(StaffingBoard staffingBoard,
      Map<SectorProfessions, Integer> sectorProfessionsQuantities) {

    var marinhoConstant = calculateMarinhoConstant(staffingBoard);
    staffingBoard.setMarinhoConstant(marinhoConstant);

    var totalHoursProcedure = calculateTotalHoursPerProcedure(staffingBoard,
        sectorProfessionsQuantities);
    staffingBoard.setTotalHours(totalHoursProcedure);

    var numberOfCollaborators = calculateNumberOfCollaborators(totalHoursProcedure,
        marinhoConstant);
    staffingBoard.setNumberOfCollaborators(numberOfCollaborators);
  }

  private static Integer calculateNumberOfCollaborators(BigDecimal totalHoursProcedure,
      BigDecimal marinhoConstant) {
    if (totalHoursProcedure != null && marinhoConstant != null) {
      var numberOfCollaborators = totalHoursProcedure.multiply(marinhoConstant)
          .setScale(0, RoundingMode.HALF_EVEN);
      return numberOfCollaborators.intValue();
    }
    return 0;
  }

  private static BigDecimal calculateTotalHoursPerProcedure(StaffingBoard staffingBoard,
      Map<SectorProfessions, Integer> sectorProfessionsQuantities) {
    var totalHoursProcedure = BigDecimal.ZERO;
    var departments = staffingBoard.getDepartment();
    for (Map.Entry<SectorProfessions, Integer> entry : sectorProfessionsQuantities.entrySet()) {
      var sectorId = entry.getKey().getSector().getId();

      var sectorFound = departments.getSectors().stream()
          .filter(sector -> sector.getId().equals(sectorId)).findFirst().orElse(null);
      if (sectorFound != null) {
        var sectorProfessionsFound = sectorFound.getSectorProfessions().stream()
            .filter(sectorProfessions -> sectorProfessions.getProfession().getId()
                .equals(entry.getKey().getProfession().getId())).findFirst().orElse(null);

        var hourProcedure = BigDecimal.ZERO;
        if (sectorProfessionsFound != null && sectorProfessionsFound.getTotalHours() != null
            && entry.getValue() != null) {
          hourProcedure = sectorProfessionsFound.getTotalHours()
              .multiply(new BigDecimal(entry.getValue())).setScale(2, RoundingMode.HALF_EVEN);
        }
        totalHoursProcedure = totalHoursProcedure.add(hourProcedure);
      }
    }
    return totalHoursProcedure;
  }

  private static BigDecimal calculateMarinhoConstant(StaffingBoard staffingBoard) {
    var daysPerWeek = staffingBoard.getDaysPerWeek().doubleValue();
    var weeklyWorkLoad = staffingBoard.getWeeklyWorkLoad().doubleValue();
    var divisor = new BigDecimal(daysPerWeek / weeklyWorkLoad);
    return divisor.multiply(TECHNICAL_SECURITY_INDEX).setScale(4, RoundingMode.HALF_EVEN);
  }
}
