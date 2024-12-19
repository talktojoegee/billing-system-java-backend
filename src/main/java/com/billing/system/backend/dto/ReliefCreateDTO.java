package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "reliefBuilder")

public class ReliefCreateDTO {

  private Long id;
  private String description;
  private String item;
  private Double rate;

}
