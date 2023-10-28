package br.com.nursingcalculator.shared.testData.hospital;

import br.com.nursingcalculator.profession.model.entity.Profession;

public final class ProfessionTestData {

  public static final String DEFAULT_PROFESSION_DESCRIPTION = "TEST ENFERMEIRO(A)";

  public static Profession createNewProfession() {
    return Profession.builder()
        .description(DEFAULT_PROFESSION_DESCRIPTION)
        .build();
  }
}
