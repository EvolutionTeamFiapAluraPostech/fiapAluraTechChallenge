package br.com.nursingcalculator.profession.presentation.dto;

import br.com.nursingcalculator.profession.model.entity.Profession;
import org.springframework.data.domain.Page;

public record ProfessionDto(
    String id,
    String description
) {

  public ProfessionDto(Profession profession) {
    this(profession.getId() != null ? profession.getId().toString() : null,
        profession.getDescription());
  }

  public static Page<ProfessionDto> toPage(Page<Profession> professionPage) {
    return professionPage.map(ProfessionDto::new);
  }
}
