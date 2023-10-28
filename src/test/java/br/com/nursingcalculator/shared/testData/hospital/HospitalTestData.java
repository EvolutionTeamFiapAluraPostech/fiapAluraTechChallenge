package br.com.nursingcalculator.shared.testData.hospital;

import br.com.nursingcalculator.hospital.model.entity.Hospital;

public final class HospitalTestData {

  public static final String DEFAULT_HOSPITAL_NAME = "TEST INTERNATIONAL HOSPITAL";

  public static Hospital createNewHospital() {
    return Hospital.builder()
        .name(DEFAULT_HOSPITAL_NAME)
        .build();
  }
}
