package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "dashboardBuilder")
@NoArgsConstructor
public class DashboardDTO {

  private long noOfProperties;
  private long noOfBills;
  private long objections;
  private double billAmount;
  private double amountPaid;



}
