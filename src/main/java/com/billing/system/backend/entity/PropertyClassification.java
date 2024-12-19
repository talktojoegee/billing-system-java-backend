package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "property_classifications")
@NoArgsConstructor
@AllArgsConstructor(staticName = "propClassBuilder")
public class PropertyClassification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;


  @OneToMany(mappedBy = "property_class", cascade = CascadeType.ALL)
  private List<PropertyAssessmentValue> pav;

/*
  @OneToMany(mappedBy = "property_class", cascade = CascadeType.ALL)
  private List<PropertyList> propertyLists;
*/




}
