package br.com.fiapbook.hospital.model.service;

import static br.com.fiapbook.hospital.model.message.DepartmentMessages.DEPARTMENT_ID_NOT_FOUND;

import br.com.fiapbook.hospital.infrastructure.repository.DepartmentRepository;
import br.com.fiapbook.hospital.model.entity.Department;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

  private final DepartmentRepository departmentRepository;

  public DepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  public Department save(Department hospital) {
    return departmentRepository.save(hospital);
  }

  public Optional<Department> findDepartmentById(UUID uuid) {
    return departmentRepository.findById(uuid);
  }

  public Department findDepartmentRequiredById(UUID uuid) {
    return departmentRepository.findById(uuid)
        .orElseThrow(() -> new NoResultException(DEPARTMENT_ID_NOT_FOUND.formatted(uuid)));
  }
}
