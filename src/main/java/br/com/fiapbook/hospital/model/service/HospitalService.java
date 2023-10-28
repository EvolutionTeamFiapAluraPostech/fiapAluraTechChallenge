package br.com.fiapbook.hospital.model.service;

import static br.com.fiapbook.hospital.model.message.HospitalMessages.HOSPITAL_ID_NOT_FOUND;

import br.com.fiapbook.hospital.infrastructure.repository.HospitalRepository;
import br.com.fiapbook.hospital.model.entity.Hospital;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

  private final HospitalRepository hospitalRepository;

  public HospitalService(HospitalRepository hospitalRepository) {
    this.hospitalRepository = hospitalRepository;
  }

  public Hospital save(Hospital hospital) {
    return hospitalRepository.save(hospital);
  }

  public Optional<Hospital> findHospitalById(UUID uuid) {
    return hospitalRepository.findById(uuid);
  }

  public Hospital findHospitalRequiredById(UUID uuid) {
    return hospitalRepository.findById(uuid)
        .orElseThrow(() -> new NoResultException(HOSPITAL_ID_NOT_FOUND.formatted(uuid)));
  }
}
