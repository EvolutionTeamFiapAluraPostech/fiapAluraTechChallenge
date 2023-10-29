package br.com.nursingcalculator.calculation.application.usecase;

import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import br.com.nursingcalculator.calculation.model.service.StaffingBoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetStaffingBoardForTestingUseCase {

  private final StaffingBoardService staffingBoardService;

  public GetStaffingBoardForTestingUseCase(StaffingBoardService staffingBoardService) {
    this.staffingBoardService = staffingBoardService;
  }

  @Transactional
  public StaffingBoard execute() {
    return staffingBoardService.findStaffingBoardTopByOrderByIdDesc();
  }
}
