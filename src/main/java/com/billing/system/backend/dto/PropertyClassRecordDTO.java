package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "dtoBuilder")
@Data
public class PropertyClassRecordDTO {

  private Long id;
  private String className;
}
