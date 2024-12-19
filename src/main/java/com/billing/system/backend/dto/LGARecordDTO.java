package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "dtoBuilder")
public class LGARecordDTO {
  private int id;
  private String lgaName;
}
