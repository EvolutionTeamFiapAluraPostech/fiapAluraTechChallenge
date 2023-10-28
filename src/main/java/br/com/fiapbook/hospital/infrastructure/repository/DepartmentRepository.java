package br.com.fiapbook.hospital.infrastructure.repository;

import br.com.fiapbook.hospital.model.entity.Department;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
