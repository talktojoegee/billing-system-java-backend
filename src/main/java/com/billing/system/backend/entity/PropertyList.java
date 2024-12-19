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
@Table(name = "property_list")
@NoArgsConstructor
@AllArgsConstructor(staticName = "propertyListBuilder")
public class PropertyList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String buildingCode;
  private String address;

  private String area;
  private String size;
  @Column(nullable = true)
  private String image;

  @Column(columnDefinition = "INT DEFAULT 0")
  private int power;
  @Column(columnDefinition = "INT DEFAULT 0")
  private int water;
  @Column(columnDefinition = "INT DEFAULT 0")
  private int borehole;
  @Column(columnDefinition = "INT DEFAULT 0")
  private int storey;

  private String refuse;

  private String ownerName;
  private String ownerEmail;
  private String ownerKgtin;
  private String ownerGsm;
  private String pavCode;
  private String title;
  private String zoneName;



  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;



  @ManyToOne
  @JoinColumn(name = "lga_id", nullable = false, insertable = true, updatable = false) // Foreign key column in Owners
  private LGA lga;


  @ManyToOne
  @JoinColumn(name = "class_id", nullable = false, insertable = true, updatable = false) // Foreign key column in Property title
  private PropertyClassification propertyClassification;

  /*@ManyToOne
  @JoinColumn(name = "owner_id", nullable = false, updatable = false, insertable = true) // Foreign key column in Owners
  private Owners owners;

  @ManyToOne
  @JoinColumn(name = "pav_id", nullable = false, insertable = true, updatable = false) // Foreign key column in PAV
  private PropertyAssessmentValue pav;

  @ManyToOne
  @JoinColumn(name = "zone_id", nullable = false, updatable = false, insertable = true)
  private Zone zone;*/

/*  @OneToMany(mappedBy = "property_list", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Billing> billing;*/


}
