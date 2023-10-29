package br.com.nursingcalculator.calculation.model.service;

import static br.com.nursingcalculator.calculation.model.message.StaffingBoardMessages.STAFFING_BOARD_ID_NOT_FOUND;

import br.com.nursingcalculator.calculation.infrastructure.repository.StaffingBoardRepository;
import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class StaffingBoardService {

  private final StaffingBoardRepository staffingBoardRepository;

  public StaffingBoardService(StaffingBoardRepository staffingBoardRepository) {
    this.staffingBoardRepository = staffingBoardRepository;
  }

  public StaffingBoard save(StaffingBoard staffingBoard) {
    return staffingBoardRepository.save(staffingBoard);
  }

  public Optional<StaffingBoard> findStaffingBoardById(UUID uuid) {
    return staffingBoardRepository.findById(uuid);
  }

  public StaffingBoard findStaffingBoardRequiredById(UUID uuid) {
    return staffingBoardRepository.findById(uuid)
        .orElseThrow(() -> new NoResultException(STAFFING_BOARD_ID_NOT_FOUND.formatted(uuid)));
  }

  public StaffingBoard findStaffingBoardTopByOrderByIdDesc() {
    return staffingBoardRepository.findTopByOrderByIdDesc();
  }
}
