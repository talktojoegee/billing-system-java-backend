package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pav") //for property assessment value
@AllArgsConstructor(staticName = "pavBuild")
@NoArgsConstructor
public class PropertyAssessmentValue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String pav_code;



  private String occupancy;
  private double assessed_amount;
  private double value_rate;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "class_id", nullable = false, updatable = false, insertable = true)
  private PropertyClassification property_class;




  // Optional: Add a reverse mapping for Owners
  /*@OneToMany(mappedBy = "pav", cascade = CascadeType.ALL)
  private List<PropertyList> propertyLists;*/


}
