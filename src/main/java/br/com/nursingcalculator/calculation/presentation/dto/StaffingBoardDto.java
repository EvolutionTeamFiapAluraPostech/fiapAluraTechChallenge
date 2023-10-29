package br.com.nursingcalculator.calculation.presentation.dto;

import br.com.nursingcalculator.calculation.model.entity.Calculation;
import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import br.com.nursingcalculator.hospital.model.entity.Department;
import br.com.nursingcalculator.hospital.model.entity.Hospital;
import br.com.nursingcalculator.hospital.model.entity.Sector;
import br.com.nursingcalculator.hospital.model.entity.SectorProfessions;
import br.com.nursingcalculator.profession.model.entity.Profession;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.util.StringUtils;

public record StaffingBoardDto(
    String id,
    String state,
    @NotNull(message = "Hospital is required.")
    String hospitalId,
    @NotNull(message = "Department is required.")
    String departmentId,
    @NotNull(message = "Profession is required.")
    String professionId,
    @NotNull(message = "Type of calculate is required.")
    String calculationId,
    List<SectorProcedureNumberDto> sectorProcedureNumberDtoList,
    @NotNull(message = "Days per week is required.")
    @Positive(message = "Days per week must be greater than zero.")
    Integer daysPerWeek,
    @NotNull(message = "Weekly work load is required.")
    @Positive(message = "Weekly work load must be greater than zero.")
    Integer weeklyWorkLoad,
    BigDecimal totalHours,
    BigDecimal marinhoConstant,
    Integer numberOfCollaborators
) {

  public StaffingBoardDto(StaffingBoard staffingBoard,
      List<SectorProcedureNumberDto> sectorProcedureNumberDtos) {
    this(staffingBoard.getId() != null ? staffingBoard.getId().toString() : null,
        staffingBoard.getCalculationState().name(),
        staffingBoard.getHospital().getId().toString(),
        staffingBoard.getDepartment().getId().toString(),
        staffingBoard.getProfession().getId().toString(),
        staffingBoard.getCalculation().getId().toString(),
        sectorProcedureNumberDtos,
        staffingBoard.getDaysPerWeek(),
        staffingBoard.getWeeklyWorkLoad(),
        staffingBoard.getTotalHours(),
        staffingBoard.getMarinhoConstant(),
        staffingBoard.getNumberOfCollaborators());
  }


  public static StaffingBoard toStaffingBoard(StaffingBoardDto staffingBoardDto) {
    var staffingBoardId = StringUtils.hasText(staffingBoardDto.id) ? UUID.fromString(staffingBoardDto.id) : null;

    var hospital = Hospital.builder()
        .id(UUID.fromString(staffingBoardDto.hospitalId))
        .build();

    var department = Department.builder()
        .id(UUID.fromString(staffingBoardDto.departmentId))
        .build();

    var profession = createProfessionFrom(staffingBoardDto);

    var calculation = Calculation.builder()
        .id(UUID.fromString(staffingBoardDto.calculationId))
        .build();

    return StaffingBoard.builder()
        .id(staffingBoardId)
        .hospital(hospital)
        .department(department)
        .profession(profession)
        .calculation(calculation)
        .daysPerWeek(staffingBoardDto.daysPerWeek)
        .weeklyWorkLoad(staffingBoardDto.weeklyWorkLoad)
        .build();
  }

  public static Map<SectorProfessions, Integer> toSectorProfessionsWithQuantities(
      StaffingBoardDto staffingBoardDto) {
    var profession = createProfessionFrom(staffingBoardDto);
    return createMapOfSectorProfessionsWithQuantities(staffingBoardDto, profession);
  }

  private static HashMap<SectorProfessions, Integer> createMapOfSectorProfessionsWithQuantities(
      StaffingBoardDto staffingBoardDto, Profession profession) {
    var sectorProfessionsWithQuantities = new HashMap<SectorProfessions, Integer>();

    for (SectorProcedureNumberDto sectorProcedureNumberDto : staffingBoardDto.sectorProcedureNumberDtoList) {
      var sector = createSectorFrom(sectorProcedureNumberDto);
      var sectorProfession = createSectorProfessionFrom(sector, profession);
      var procedureQuantities = sectorProcedureNumberDto.quantitiesOfProcedures();
      sectorProfessionsWithQuantities.put(sectorProfession, procedureQuantities);
    }

    return sectorProfessionsWithQuantities;
  }

  private static Profession createProfessionFrom(StaffingBoardDto staffingBoardDto) {
    return Profession.builder()
        .id(UUID.fromString(staffingBoardDto.professionId))
        .build();
  }

  private static Sector createSectorFrom(SectorProcedureNumberDto sectorProcedureNumberDto) {
    return Sector.builder()
        .id(UUID.fromString(sectorProcedureNumberDto.sectorId()))
        .build();
  }

  private static SectorProfessions createSectorProfessionFrom(Sector sector,
      Profession profession) {
    return SectorProfessions.builder()
        .sector(sector)
        .profession(profession)
        .build();
  }

  public static StaffingBoardDto from(StaffingBoard staffingBoard,
      List<SectorProcedureNumberDto> sectorProcedureNumberDtos) {
    return new StaffingBoardDto(staffingBoard, sectorProcedureNumberDtos);
  }
}
