package com.billing.system.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyTitleCreateDTO {

  @NotBlank(message = "Title cannot be blank")
  @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
  private String title;

}
