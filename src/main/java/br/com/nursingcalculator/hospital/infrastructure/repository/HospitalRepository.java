package br.com.nursingcalculator.hospital.infrastructure.repository;

import br.com.nursingcalculator.hospital.model.entity.Hospital;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {

  Hospital findTopByOrderByIdDesc();
}
