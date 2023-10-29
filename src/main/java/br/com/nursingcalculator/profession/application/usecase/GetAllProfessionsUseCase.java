package br.com.nursingcalculator.profession.application.usecase;

import br.com.nursingcalculator.profession.model.entity.Profession;
import br.com.nursingcalculator.profession.model.service.ProfessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllProfessionsUseCase {

  private final ProfessionService professionService;

  public GetAllProfessionsUseCase(ProfessionService professionService) {
    this.professionService = professionService;
  }

  public Page<Profession> execute(Pageable pageable) {
    return professionService.getAllProfessionsPaginated(pageable);
  }
}
