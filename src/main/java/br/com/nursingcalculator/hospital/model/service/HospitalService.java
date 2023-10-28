package br.com.nursingcalculator.hospital.model.service;

import static br.com.nursingcalculator.hospital.model.message.HospitalMessages.HOSPITAL_ID_NOT_FOUND;

import br.com.nursingcalculator.hospital.infrastructure.repository.HospitalRepository;
import br.com.nursingcalculator.hospital.model.entity.Hospital;
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
