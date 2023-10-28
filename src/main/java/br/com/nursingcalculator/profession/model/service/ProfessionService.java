package br.com.nursingcalculator.profession.model.service;

import static br.com.nursingcalculator.profession.model.message.ProfessionMessages.PROFESSION_ID_NOT_FOUND;

import br.com.nursingcalculator.profession.infrastructure.repository.ProfessionRepository;
import br.com.nursingcalculator.profession.model.entity.Profession;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProfessionService {

  private final ProfessionRepository professionRepository;

  public ProfessionService(ProfessionRepository professionRepository) {
    this.professionRepository = professionRepository;
  }

  public Profession save(Profession profession) {
    return professionRepository.save(profession);
  }

  public Optional<Profession> findProfessionById(UUID uuid) {
    return professionRepository.findById(uuid);
  }

  public Profession findProfessionRequiredById(UUID uuid) {
    return professionRepository.findById(uuid)
        .orElseThrow(() -> new NoResultException(PROFESSION_ID_NOT_FOUND.formatted(uuid)));
  }
}
