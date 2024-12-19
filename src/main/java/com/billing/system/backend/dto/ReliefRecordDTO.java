package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "dtoBuilder")

public class ReliefRecordDTO {

  private Long id;
  private String description;
  private String item;
  private Double rate;
}
