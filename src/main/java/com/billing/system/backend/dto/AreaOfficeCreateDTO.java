package com.billing.system.backend.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaOfficeCreateDTO {

  @NotNull(message = "What is the area office ID?")
  private String areaOfficeId;
  @NotNull(message = "Area office name is required")
  private String areaName;
  @NotNull(message = "Indicate the LGA to which this belongs to")
  private int lgaId;


}
