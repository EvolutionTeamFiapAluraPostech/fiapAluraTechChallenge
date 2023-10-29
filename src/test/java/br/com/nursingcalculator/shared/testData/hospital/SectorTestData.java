package br.com.nursingcalculator.shared.testData.hospital;

import br.com.nursingcalculator.hospital.model.entity.Sector;

public final class SectorTestData {

  public static final String DEFAULT_SECTOR_NAME = "TEST RAIO-X";

  public static Sector createNewSector() {
    return Sector.builder()
        .name(DEFAULT_SECTOR_NAME)
        .build();
  }
}
