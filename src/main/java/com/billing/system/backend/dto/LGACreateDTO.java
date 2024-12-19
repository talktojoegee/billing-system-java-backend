package com.billing.system.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LGACreateDTO {

  @NotNull(message = "LGA name cannot be blank")
  @Size(min = 2, max = 100, message = "LGA name must be between 2 and 100 characters")
  private String lgaName;

}
