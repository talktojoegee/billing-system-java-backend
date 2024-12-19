package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "billingRecordBuilder")
public class BillingRecordDTO {

  private Integer id;
  private String buildingCode;
  private String assessmentNo;
  private Double assessedValue;
  private Double billAmount;
  private String date;
  private Integer paid;
  private Double paidAmount;
  private String objection;
  private int year;

  private String lgaName;

  public BillingRecordDTO(String s) {
  }
  //private String buildingCode;

}
