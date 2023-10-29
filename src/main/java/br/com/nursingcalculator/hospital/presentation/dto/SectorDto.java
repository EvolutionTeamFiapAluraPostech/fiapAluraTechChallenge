package br.com.nursingcalculator.hospital.presentation.dto;

import br.com.nursingcalculator.hospital.model.entity.Sector;
import org.springframework.data.domain.Page;

public record SectorDto(
    String id,
    String name
) {

  public SectorDto(Sector sector) {
    this(sector.getId() != null ? sector.getId().toString() : null,
        sector.getName());
  }

  public static Page<SectorDto> toPage(Page<Sector> sectorsPage) {
    return sectorsPage.map(SectorDto::new);
  }
}
