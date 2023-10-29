package br.com.nursingcalculator.hospital.application.usecase;

import br.com.nursingcalculator.hospital.model.entity.Sector;
import br.com.nursingcalculator.hospital.model.service.SectorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllSectorsUseCase {

  private final SectorService sectorService;

  public GetAllSectorsUseCase(SectorService sectorService) {
    this.sectorService = sectorService;
  }

  public Page<Sector> execute(Pageable pageable) {
    return sectorService.getAllSectorsPaginated(pageable);
  }
}
