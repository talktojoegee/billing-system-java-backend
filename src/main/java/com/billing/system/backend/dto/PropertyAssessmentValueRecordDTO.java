package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "dtoBuilder")
@NoArgsConstructor
public class PropertyAssessmentValueRecordDTO {

  private int id;
  private String pavCode;
  private String description;
  private double assessedAmount;
  private double valueRate;
  private String className; // This will hold the class_name from Propert

}
