package br.com.nursingcalculator.hospital.model.service;

import static br.com.nursingcalculator.hospital.model.message.SectorMessages.SECTOR_ID_NOT_FOUND;

import br.com.nursingcalculator.hospital.infrastructure.repository.SectorRepository;
import br.com.nursingcalculator.hospital.model.entity.Sector;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

  private final SectorRepository sectorRepository;

  public SectorService(SectorRepository sectorRepository) {
    this.sectorRepository = sectorRepository;
  }

  public Sector save(Sector hospital) {
    return sectorRepository.save(hospital);
  }

  public Optional<Sector> findSectorById(UUID uuid) {
    return sectorRepository.findById(uuid);
  }

  public Sector findSectorRequiredById(UUID uuid) {
    return sectorRepository.findById(uuid)
        .orElseThrow(() -> new NoResultException(SECTOR_ID_NOT_FOUND.formatted(uuid)));
  }
}
