package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zones")
@AllArgsConstructor(staticName = "zoneBuilder")
@NoArgsConstructor
public class Zone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String zone;
  @Column
  private String sub_zone;
/*
  @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PropertyList> propertyLists;*/

}
