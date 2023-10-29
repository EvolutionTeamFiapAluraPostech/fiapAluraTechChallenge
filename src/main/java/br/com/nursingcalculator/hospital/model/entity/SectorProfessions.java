package br.com.nursingcalculator.hospital.model.entity;

import br.com.nursingcalculator.profession.model.entity.Profession;
import br.com.nursingcalculator.shared.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "hospital_management", name = "sector_professions")
public class SectorProfessions extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "sector_id")
  private Sector sector;

  @ManyToOne
  @JoinColumn(name = "profession_id")
  private Profession profession;

  private BigDecimal totalHours;
}
