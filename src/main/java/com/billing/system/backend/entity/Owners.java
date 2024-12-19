package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owners")
@NoArgsConstructor
@AllArgsConstructor(staticName = "ownersBuild")
@Data
public class Owners {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String kgtin;
  private String name;
  private String telephone;
  private String email;
  private String resAddress;



  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lga_id", nullable = false, updatable = false, insertable = true) // Foreign key column in Owners
  private LGA lga;

/*
  @OneToMany(mappedBy = "owners", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PropertyList> propertyList;*/




}
