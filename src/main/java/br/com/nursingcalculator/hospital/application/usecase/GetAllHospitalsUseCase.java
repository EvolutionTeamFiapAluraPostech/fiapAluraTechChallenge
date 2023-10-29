package br.com.nursingcalculator.hospital.application.usecase;

import br.com.nursingcalculator.hospital.model.entity.Hospital;
import br.com.nursingcalculator.hospital.model.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllHospitalsUseCase {

  private final HospitalService hospitalService;

  public GetAllHospitalsUseCase(HospitalService hospitalService) {
    this.hospitalService = hospitalService;
  }

  public Page<Hospital> execute(Pageable pageable) {
    return hospitalService.getAllHospitalsPaginated(pageable);
  }
}
