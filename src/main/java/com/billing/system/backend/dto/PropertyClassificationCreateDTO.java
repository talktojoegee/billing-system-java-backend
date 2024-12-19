package com.billing.system.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyClassificationCreateDTO {

  @NotBlank(message = "Class name cannot be blank")
  @Size(min = 2, max = 100, message = "Class name must be between 2 and 100 characters")
  private String className;


}
