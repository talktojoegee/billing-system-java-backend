package com.billing.system.backend.service;

import com.billing.system.backend.dto.DashboardDTO;
import com.billing.system.backend.repository.BillingRepository;
import com.billing.system.backend.repository.PropertyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {


  @Autowired
  private final PropertyListRepository propertyListRepository;
  @Autowired
  private final BillingRepository billingRepository;


  public DashboardService(PropertyListRepository propertyListRepository, BillingRepository billingRepository) {
    this.propertyListRepository = propertyListRepository;
    this.billingRepository = billingRepository;
  }


  public DashboardDTO fetchDashboardValues(){
    long noOfProperties = propertyListRepository.countAllProperties();
    long noOfBills = billingRepository.countAllBills();
    long noOfObjections = billingRepository.countAllObjectionBills(1);
    double billAmount = billingRepository.sumBillAmount();
    double paidAmount = billingRepository.sumPaidAmount();

    return DashboardDTO.dashboardBuilder(
      noOfProperties,
      noOfBills,
      noOfObjections,
      billAmount,
      paidAmount
    );


  }
}
