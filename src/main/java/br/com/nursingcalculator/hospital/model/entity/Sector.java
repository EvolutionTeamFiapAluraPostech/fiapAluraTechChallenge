package br.com.nursingcalculator.hospital.model.entity;

import br.com.nursingcalculator.shared.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
@Table(schema = "hospital_management", name = "sectors")
public class Sector extends BaseEntity {

  @Column(nullable = false)
  private Boolean active = true;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "sector", fetch = FetchType.LAZY)
  private List<SectorProfessions> professions = new ArrayList<>();
}
