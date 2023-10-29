package br.com.nursingcalculator.calculation.model.entity;

import br.com.nursingcalculator.hospital.model.entity.Department;
import br.com.nursingcalculator.hospital.model.entity.Hospital;
import br.com.nursingcalculator.profession.model.entity.Profession;
import br.com.nursingcalculator.shared.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(schema = "calculation_management", name = "staffing_board")
public class StaffingBoard extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CalculationState calculationState;

  @ManyToOne
  @JoinColumn(name = "hospital_id")
  private Hospital hospital;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToOne
  @JoinColumn(name = "profession_id")
  private Profession profession;

  @ManyToOne
  @JoinColumn(name = "calculation_id")
  private Calculation calculation;

  private BigDecimal totalHours;

  private BigDecimal marinhoConstant;

  private Integer numberOfCollaborators;

  private String calculationMemory;
}
