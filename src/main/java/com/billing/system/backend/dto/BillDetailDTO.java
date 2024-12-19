package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "billDetailBuilder")
@NoArgsConstructor
public class BillDetailDTO {

  private String ownerName;
  private String buildingCode;
  private String contactAddress;
  private String propertyClassification;
  private String kgTin;
  private String entryDate;
  private String assessmentNo;
  private String propertyAddress;
  private String phoneNo;
  private Double assessValue;
  private Double chargeRate;
  private int year;
  private Double billAmount;

}
