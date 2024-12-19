package com.billing.system.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyListCreateDTO {

  @NotNull(message = "Enter building code")
  private String buildingCode;

  @NotNull(message = "Enter address")
  private String address;

  @NotNull(message = "Indicate LGA")
  private Integer lga;

  @NotNull(message = "Enter or choose area")
  private String area;

  @NotNull(message = "Indicate size")
  private String size;

  //@NotNull(message = "Upload an image")
  private String image;


  private int power;
  private int water;
  private int borehole;
  private int storey;
  private String refuse;

  private String ownerName;
  private String ownerEmail;
  private String ownerKgtin;
  private String ownerGsm;
  private String pavCode;
  private String title;
  private String zoneName;
  private String classId;


  /*@NotNull(message = "Property title is required")
  private Integer title;

  @NotNull(message = "Owner ID is required")
  private Integer owner;

  @NotNull(message = "PAV code is required")
  private Integer pavCode;

  private Integer zoneId;*/

}
