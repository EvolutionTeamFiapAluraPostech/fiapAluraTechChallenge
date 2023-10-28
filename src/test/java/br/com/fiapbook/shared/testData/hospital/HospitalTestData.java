package br.com.fiapbook.shared.testData.hospital;

import br.com.fiapbook.hospital.model.entity.Hospital;

public final class HospitalTestData {

  public static final String DEFAULT_HOSPITAL_NAME = "TEST INTERNATIONAL HOSPITAL";

  public static Hospital createNewHospital() {
    return Hospital.builder()
        .name(DEFAULT_HOSPITAL_NAME)
        .build();
  }
}
