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
@Table(name = "property_titles")
@NoArgsConstructor
@AllArgsConstructor(staticName = "propertyTitleBuilder")
public class PropertyTitle {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;


  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;


  // Optional: Add a reverse mapping for Owners
/*  @OneToMany(mappedBy = "property_title", cascade = CascadeType.ALL)
  private List<PropertyList> propertyLists;*/

}
