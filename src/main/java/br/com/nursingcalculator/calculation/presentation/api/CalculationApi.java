package br.com.nursingcalculator.calculation.presentation.api;

import br.com.nursingcalculator.calculation.application.usecase.GetAllCalculationsUseCase;
import br.com.nursingcalculator.calculation.presentation.dto.CalculationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculations")
public class CalculationApi {

  private final GetAllCalculationsUseCase getAllCalculationsUseCase;

  public CalculationApi(GetAllCalculationsUseCase getAllCalculationsUseCase) {
    this.getAllCalculationsUseCase = getAllCalculationsUseCase;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<CalculationDto> getAllCalculationsPaginated(
      @PageableDefault(sort = {"description"}) Pageable pageable) {
    var professionPage = getAllCalculationsUseCase.execute(pageable);
    return CalculationDto.toPage(professionPage);
  }

}
