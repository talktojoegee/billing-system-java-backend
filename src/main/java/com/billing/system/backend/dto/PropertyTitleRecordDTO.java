package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "titleBuilder")
@Data
public class PropertyTitleRecordDTO {

  private String title;
  private int id;
}
