package br.com.nursingcalculator.hospital.infrastructure.repository;

import br.com.nursingcalculator.hospital.model.entity.Department;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
