package com.billing.system.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAssessmentValueCreateDTO {

  @NotBlank(message = "PAV code cannot be blank")
  @Size(min = 2, max = 50, message = "PAV code must be between 2 and 50 characters")
  private String pav_code;

  @NotBlank(message = "Occupancy cannot be blank")
  private String occupancy;

  @NotNull(message = "Assessed amount cannot be null")
  @Positive(message = "Assessed amount must be a positive number")
  private Double assessed_amount;

  @NotNull(message = "Value rate cannot be null")
  @Positive(message = "Value rate must be a positive number")
  private Double value_rate;

  @NotNull(message = "Property classification ID cannot be null")
  private Integer class_id;
  private String className; // This is the new field to store class_name
}
