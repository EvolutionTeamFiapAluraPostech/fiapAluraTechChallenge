package br.com.nursingcalculator.calculation.infrastructure.repository;

import br.com.nursingcalculator.calculation.model.entity.StaffingBoard;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffingBoardRepository extends JpaRepository<StaffingBoard, UUID> {

}
