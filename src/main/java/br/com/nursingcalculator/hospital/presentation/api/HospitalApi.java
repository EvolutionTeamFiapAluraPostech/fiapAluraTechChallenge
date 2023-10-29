package br.com.nursingcalculator.hospital.presentation.api;

import br.com.nursingcalculator.hospital.application.usecase.GetAllHospitalsUseCase;
import br.com.nursingcalculator.hospital.presentation.dto.HospitalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitals")
public class HospitalApi {

  private final GetAllHospitalsUseCase getAllHospitalsUseCase;

  public HospitalApi(GetAllHospitalsUseCase getAllHospitalsUseCase) {
    this.getAllHospitalsUseCase = getAllHospitalsUseCase;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<HospitalDto> getAllHospitalsPaginated(
      @PageableDefault(sort = {"name"}) Pageable pageable) {
    var hospitalPage = getAllHospitalsUseCase.execute(pageable);
    return HospitalDto.toPage(hospitalPage);
  }

}
