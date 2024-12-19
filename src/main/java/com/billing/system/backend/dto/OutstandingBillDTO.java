package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "outstandingBuilder")
@NoArgsConstructor
public class OutstandingBillDTO {

  private int billId;
  private String assessmentNo;
  private String buildingCode;
  private Integer year;
  private String zoneName;
  private String categoryName;
  private String owner;
  private Double billAmount;
  private Double balance;
  private String lgaName;
}
