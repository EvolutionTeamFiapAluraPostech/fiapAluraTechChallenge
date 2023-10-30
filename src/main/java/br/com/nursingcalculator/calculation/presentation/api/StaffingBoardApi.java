package br.com.nursingcalculator.calculation.presentation.api;

import br.com.nursingcalculator.calculation.application.usecase.GetStaffingBoardByIdUseCase;
import br.com.nursingcalculator.calculation.application.usecase.GetStaffingBoardForTestingUseCase;
import br.com.nursingcalculator.calculation.application.usecase.StaffingBoardCalculateUseCase;
import br.com.nursingcalculator.calculation.presentation.dto.StaffingBoardDto;
import br.com.nursingcalculator.calculation.presentation.dto.StaffingBoardTestingDto;
import jakarta.validation.Valid;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffing-board-calculate")
public class StaffingBoardApi {

  private final StaffingBoardCalculateUseCase staffingBoardCalculateUseCase;
  private final GetStaffingBoardByIdUseCase getStaffingBoardByIdUseCase;
  private final GetStaffingBoardForTestingUseCase getStaffingBoardForTestingUseCase;

  public StaffingBoardApi(StaffingBoardCalculateUseCase staffingBoardCalculateUseCase,
      GetStaffingBoardByIdUseCase getStaffingBoardByIdUseCase,
      GetStaffingBoardForTestingUseCase getStaffingBoardForTestingUseCase) {
    this.staffingBoardCalculateUseCase = staffingBoardCalculateUseCase;
    this.getStaffingBoardByIdUseCase = getStaffingBoardByIdUseCase;
    this.getStaffingBoardForTestingUseCase = getStaffingBoardForTestingUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public StaffingBoardDto calculateStaffingBoard(
      @RequestBody @Valid StaffingBoardDto staffingBoardDto) {
    var staffingBoard = StaffingBoardDto.toStaffingBoard(staffingBoardDto);
    var sectorProfessionsQuantities = StaffingBoardDto.toSectorProfessionsWithQuantities(
        staffingBoardDto);
    var staffingBoardUpdated = staffingBoardCalculateUseCase.execute(staffingBoard,
        sectorProfessionsQuantities);
    return StaffingBoardDto.from(staffingBoardUpdated,
        staffingBoardDto.sectorProcedureNumberDtoList());
  }

  @GetMapping("/{calculationId}")
  public StaffingBoardDto getCalculation(@PathVariable String calculationId) {
    var staffingBoard = getStaffingBoardByIdUseCase.execute(calculationId);
    return StaffingBoardDto.from(staffingBoard, Collections.emptyList());
  }

  @GetMapping("/generate-json-request-body-for-manual-testing")
  public StaffingBoardTestingDto generateJsonRequestBodyForManualTest(
      @RequestParam Integer daysOfWeek, @RequestParam Integer weeklyWorkLoad) {
    var staffingBoard = getStaffingBoardForTestingUseCase.execute(daysOfWeek, weeklyWorkLoad);
    var department = staffingBoard.getDepartment();
    var sectors = department.getSectors();
    var sectorProfessions = sectors.stream()
        .flatMap(sector -> sector.getSectorProfessions().stream()).toList();
    return StaffingBoardTestingDto.from(staffingBoard, sectorProfessions);
  }
}
