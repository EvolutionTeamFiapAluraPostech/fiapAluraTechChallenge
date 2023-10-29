package br.com.nursingcalculator.profession.presentation.api;

import br.com.nursingcalculator.profession.application.usecase.GetAllProfessionsUseCase;
import br.com.nursingcalculator.profession.presentation.dto.ProfessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professions")
public class ProfessionApi {

  private final GetAllProfessionsUseCase getAllProfessionsUseCase;

  public ProfessionApi(GetAllProfessionsUseCase getAllProfessionsUseCase) {
    this.getAllProfessionsUseCase = getAllProfessionsUseCase;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<ProfessionDto> getAllProfessionsPaginated(
      @PageableDefault(sort = {"description"}) Pageable pageable) {
    var professionPage = getAllProfessionsUseCase.execute(pageable);
    return ProfessionDto.toPage(professionPage);
  }

}
