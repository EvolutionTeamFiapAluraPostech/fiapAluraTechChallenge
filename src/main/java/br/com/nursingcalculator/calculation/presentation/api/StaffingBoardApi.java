package br.com.nursingcalculator.calculation.presentation.api;

import br.com.nursingcalculator.calculation.application.usecase.StaffingBoardCalculateUseCase;
import br.com.nursingcalculator.calculation.presentation.dto.StaffingBoardDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffing-board-calculate")
public class StaffingBoardApi {

  private final StaffingBoardCalculateUseCase staffingBoardCalculateUseCase;

  public StaffingBoardApi(StaffingBoardCalculateUseCase staffingBoardCalculateUseCase) {
    this.staffingBoardCalculateUseCase = staffingBoardCalculateUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public StaffingBoardDto calculateStaffingBoard(@RequestBody @Valid StaffingBoardDto staffingBoardDto) {
    var staffingBoard = StaffingBoardDto.toStaffingBoard(staffingBoardDto);
    var sectorProfessionsQuantities = StaffingBoardDto.toSectorProfessionsWithQuantities(staffingBoardDto);
    var staffingBoardUpdated = staffingBoardCalculateUseCase.execute(staffingBoard, sectorProfessionsQuantities);
    return StaffingBoardDto.from(staffingBoardUpdated, staffingBoardDto.sectorProcedureNumberDtoList());
  }
}
