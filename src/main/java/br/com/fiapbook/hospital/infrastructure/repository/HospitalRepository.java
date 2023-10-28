package br.com.fiapbook.hospital.infrastructure.repository;

import br.com.fiapbook.hospital.model.entity.Hospital;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {

}
