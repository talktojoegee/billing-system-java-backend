package com.billing.system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Building {

  private int fid;
  private String id;
  private String Bld_ID;
  private String Owner;
  private String Street;
  private String LGA;
  private String Zone;
  private String Bld_Cat;
  private String Bld_Storey;
  private String Occupant;
  private String Photo;
  private String Sign_Post;
  private String Bld_Age;
  private String Borehole;
  private String Water;
  private String DataEntry;
  private String Power;
  private String Bld_area;
  private String KGTIN;
  private String GSM_of_Own;
  private String Pay_Status;

  @Override
  public String toString() {
    return "Building{" +
      "fid=" + fid +
      ", Owner='" + Owner + '\'' +
      ", Zone='" + Zone + '\'' +
      ", Pay_Status='" + Pay_Status + '\'' +
      '}';
  }
}
