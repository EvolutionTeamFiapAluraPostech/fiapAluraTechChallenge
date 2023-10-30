package br.com.nursingcalculator.profession.infrastructure.repository;

import br.com.nursingcalculator.profession.model.entity.Profession;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, UUID> {

  Profession findByDescription(String description);
}
