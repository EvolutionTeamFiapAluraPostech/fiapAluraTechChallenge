package br.com.nursingcalculator.hospital.infrastructure.repository;

import br.com.nursingcalculator.hospital.model.entity.Sector;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, UUID> {

}
