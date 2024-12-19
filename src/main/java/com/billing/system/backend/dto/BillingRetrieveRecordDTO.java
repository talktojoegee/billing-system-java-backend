package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "retrieveBuilder")
public class BillingRetrieveRecordDTO {


  private Double billAmount;
  private String lgaName;
  private long noOfBuildings;
  private long noOfBills;
}
