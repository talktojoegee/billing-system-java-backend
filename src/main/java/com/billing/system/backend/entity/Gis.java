package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gis")
@AllArgsConstructor(staticName = "gisBuilder")
@NoArgsConstructor
public class Gis {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int fid;
  @Column
  private String Bld_ID;
  @Column
  private String owner;
  @Column
  private String street;
  @Column
  private String lga;
  @Column
  private String zone;
  @Column
  private String Bld_Cat;
  @Column
  private int Bld_Storey;
  @Column
  private String Occupant;
  @Column
  private String Photo;
  @Column
  private String Sign_Post;
  @Column
  private int Bld_Age;
  @Column
  private String Borehole;
  @Column
  private String Water;
  @Column
  private String DataEntry;
  @Column
  private String Power;
  @Column
  private String Bld_area;
  @Column
  private String KGTIN;
  @Column
  private String GSM_of_Own;
  @Column
  private String Pay_Status;
}
