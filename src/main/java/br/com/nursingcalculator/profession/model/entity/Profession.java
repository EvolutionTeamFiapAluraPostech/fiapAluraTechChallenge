package br.com.nursingcalculator.profession.model.entity;

import br.com.nursingcalculator.shared.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(schema = "profession_management", name = "professions")
public class Profession extends BaseEntity {

  @Column(nullable = false)
  private Boolean active = true;

  @Column(nullable = false)
  private String description;
}
