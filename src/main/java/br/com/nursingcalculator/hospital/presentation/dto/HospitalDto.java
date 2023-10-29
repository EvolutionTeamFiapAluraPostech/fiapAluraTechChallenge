package br.com.nursingcalculator.hospital.presentation.dto;

import br.com.nursingcalculator.hospital.model.entity.Hospital;
import org.springframework.data.domain.Page;

public record HospitalDto(
    String id,
    String name
) {

  public HospitalDto(Hospital hospital) {
    this(hospital.getId() != null ? hospital.getId().toString() : null,
        hospital.getName());
  }

  public static Page<HospitalDto> toPage(Page<Hospital> hospitalsPage) {
    return hospitalsPage.map(HospitalDto::new);
  }
}
