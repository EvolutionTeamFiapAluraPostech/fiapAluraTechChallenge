package br.com.nursingcalculator.calculation.presentation.dto;

import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import br.com.nursingcalculator.hospital.model.entity.SectorProfessions;
import java.util.ArrayList;
import java.util.List;

public record StaffingBoardTestingDto(
    String id,
    String state,
    String hospitalId,
    String departmentId,
    String professionId,
    String calculationId,
    List<SectorProcedureTestingDto> sectorProcedureNumberDtoList,
    Integer daysPerWeek,
    Integer weeklyWorkLoad
) {

  public StaffingBoardTestingDto(StaffingBoard staffingBoard,
      List<SectorProcedureTestingDto> sectorProcedureTestingDto) {
    this(staffingBoard.getId() != null ? staffingBoard.getId().toString() : null,
        staffingBoard.getCalculationState().name(),
        staffingBoard.getHospital().getId().toString(),
        staffingBoard.getDepartment().getId().toString(),
        staffingBoard.getProfession().getId().toString(),
        staffingBoard.getCalculation().getId().toString(),
        sectorProcedureTestingDto,
        staffingBoard.getDaysPerWeek(),
        staffingBoard.getWeeklyWorkLoad());
  }

  public static StaffingBoardTestingDto from(StaffingBoard staffingBoard,
      List<SectorProfessions> sectorProfessions) {
    var sectorProcedureTestingListDto = new ArrayList<SectorProcedureTestingDto>();
    var sectorProfessionsPerProfession = sectorProfessions.stream()
        .filter(sp -> sp.getProfession().equals(staffingBoard.getProfession())).toList();
    var quantity = 10;
    for (SectorProfessions sectorProfession : sectorProfessionsPerProfession) {
      var sectorProcedureTestingDto = new SectorProcedureTestingDto(
          sectorProfession.getSector().getId().toString(), quantity);
      sectorProcedureTestingListDto.add(sectorProcedureTestingDto);
      quantity += 10;
    }
    return new StaffingBoardTestingDto(staffingBoard, sectorProcedureTestingListDto);
  }
}
