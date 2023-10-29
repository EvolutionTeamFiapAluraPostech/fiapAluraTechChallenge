package br.com.nursingcalculator.calculation.application.usecase;

import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import br.com.nursingcalculator.calculation.model.service.StaffingBoardService;
import br.com.nursingcalculator.shared.model.entity.validator.UuidValidator;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GetStaffingBoardByIdUseCase {

  private final StaffingBoardService staffingBoardService;
  private final UuidValidator uuidValidator;

  public GetStaffingBoardByIdUseCase(StaffingBoardService staffingBoardService,
      UuidValidator uuidValidator) {
    this.staffingBoardService = staffingBoardService;
    this.uuidValidator = uuidValidator;
  }

  public StaffingBoard execute(String uuid) {
    uuidValidator.validate(uuid);
    return staffingBoardService.findStaffingBoardRequiredById(UUID.fromString(uuid));
  }
}
