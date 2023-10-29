package br.com.nursingcalculator.hospital.presentation.api;

import br.com.nursingcalculator.hospital.application.usecase.GetAllSectorsUseCase;
import br.com.nursingcalculator.hospital.presentation.dto.SectorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sectors")
public class SectorApi {

  private final GetAllSectorsUseCase getAllSectorsUseCase;

  public SectorApi(GetAllSectorsUseCase getAllSectorsUseCase) {
    this.getAllSectorsUseCase = getAllSectorsUseCase;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<SectorDto> getAllDepartmentsPaginated(
      @PageableDefault(sort = {"name"}) Pageable pageable) {
    var sectorsPage = getAllSectorsUseCase.execute(pageable);
    return SectorDto.toPage(sectorsPage);
  }

}
