package com.billing.system.backend.service;

import com.billing.system.backend.dto.*;
import com.billing.system.backend.entity.Billing;
import com.billing.system.backend.entity.LGA;
import com.billing.system.backend.entity.PropertyAssessmentValue;
import com.billing.system.backend.entity.PropertyList;
import com.billing.system.backend.repository.BillingRepository;
import com.billing.system.backend.repository.LGARepository;
import com.billing.system.backend.repository.PropertyAssessmentValueRepository;
import com.billing.system.backend.repository.PropertyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillingService {


  private final BillingRepository billingRepository;
  private final PropertyListRepository propertyListRepository;
  private final PropertyAssessmentValueRepository propertyAssessmentValueRepository;
  private final LGARepository lgaRepository;

  @Autowired
  public BillingService(BillingRepository billingRepository,
                        PropertyListRepository propertyListRepository,
                        PropertyAssessmentValueRepository propertyAssessmentValueRepository,
                        LGARepository lgaRepository) {
    this.billingRepository = billingRepository;
    this.propertyListRepository = propertyListRepository;
    this.propertyAssessmentValueRepository = propertyAssessmentValueRepository;
    this.lgaRepository = lgaRepository;
  }

  public List<?> storeBill(BillingCreateDTO dto) {
    List<PropertyList> propertyLists = propertyListRepository.findByLga_Id(dto.getLgaId());
    if (propertyLists.isEmpty()) {
      throw new IllegalArgumentException("There is nothing to process.");
    }
    // Check if a bill for the specified year and LGA already exists
    List<Billing> existingBills = billingRepository.findAllByYearAndLgaId(dto.getYear(), dto.getLgaId());
    if (!existingBills.isEmpty()) {
      // Return the existing bills if they have already been processed
      throw new IllegalStateException("Bill for the specified year and LGA has already been processed.");
    }

          //List<BillingRecordDTO> billingDTOs = new ArrayList<>();
          for (PropertyList property : propertyLists) {

            Optional<PropertyAssessmentValue> pavOptional = propertyAssessmentValueRepository.findByPavCode(property.getPavCode());
            Optional<LGA> lga = lgaRepository.findById(property.getLga().getId());

                  if (pavOptional.isPresent() && lga.isPresent()) {
                    PropertyAssessmentValue pav = pavOptional.get();
                    String uniqueNumber = UUID.randomUUID().toString().replace("-", "");

                    double billAmount = (pav.getValue_rate()/100) * pav.getAssessed_amount();
                    Billing billing = new Billing();
                    billing.setBuilding_code(property.getBuildingCode());
                    billing.setAssessment_no(uniqueNumber);
                    billing.setAssessed_value(pav.getAssessed_amount());
                    billing.setBill_amount(billAmount);
                    billing.setYear(dto.getYear());
                    billing.setDate(String.valueOf(LocalDateTime.now()));
                    billing.setBilled_by("1");
                    billing.setPaid(0);
                    billing.setPaid_amount(0.00);
                    billing.setObjection("0");
                    billing.setCreatedAt(LocalDateTime.now());
                    billing.setUpdatedAt(LocalDateTime.now());
                    billing.setLga(lga.get());
                    billing.setPropertyList(property);
                    billing.setBill_rate(pav.getValue_rate());
                    billing.setPav_code(pav.getPav_code());
                   billingRepository.save(billing); //save record
                }

          }

    //Get bills
    List<Billing> billingList = billingRepository.findAll();

    return billingList.stream().map(ls->{
      String lgaName = ls.getLga() != null ? ls.getLga().getLga_name() : null;
      //String property = ls.getPropertyList() != null ? ls.getBuilding_code() : null;
      return BillingRecordDTO.billingRecordBuilder(
        Math.toIntExact(ls.getB_id()),
        ls.getBuilding_code(),
        ls.getAssessment_no(),
        ls.getAssessed_value(),
        ls.getBill_amount(),
        ls.getDate(),
        ls.getPaid(),
        ls.getPaid_amount(),
        ls.getObjection(),
        ls.getYear(),
        lgaName
      );
    }).collect(Collectors.toList());


  }



  public List<BillingRetrieveRecordDTO> fetchBillSummaryByYearAndLga(int year, int lgaId) {
    // Fetch the summarized billing data
    List<Object[]> summaryData = billingRepository.findBillSummaryByYearAndLgaId(year, lgaId);

    // Return an empty list if no data is found
    if (summaryData.isEmpty()) {
      return Collections.emptyList();
    }

    // Map the raw data to BillingRetrieveRecordDTOs
    return summaryData.stream()
      .map(data -> BillingRetrieveRecordDTO.retrieveBuilder(
        (Double) data[1],           // billAmount
        getLgaName((Integer) data[0]), // lgaName
        getBuildingCount((Integer) data[0]),
        (Long) data[2]             // noOfBills
      ))
      .collect(Collectors.toList());
  }

  // Helper method to fetch LGA name
  private String getLgaName(int lgaId) {
    return lgaRepository.findById(lgaId)
      .map(LGA::getLga_name)
      .orElse("Unknown LGA");
  }


  private long getBuildingCount(int lgaId) {
    return propertyListRepository.countByLgaId(lgaId);
  }


  public BillDetailDTO fetchOneBillById(int id) {
    Billing bill = billingRepository.findById((long) id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));

    // Extract LGA name if it exists
    String lgaName = (bill.getLga() != null) ? bill.getLga().getLga_name() : null;
    // Map the Billing entity to BillingRecordDTO
    return BillDetailDTO.billDetailBuilder(
      bill.getPropertyList().getOwnerName(),
      bill.getPropertyList().getBuildingCode(),
      bill.getPropertyList().getAddress(),
      bill.getPropertyList().getPropertyClassification().getName(),
      bill.getPropertyList().getOwnerKgtin(),
      bill.getDate(),
      bill.getAssessment_no(),
      bill.getPropertyList().getAddress(),
      "234",
      bill.getAssessed_value(),
      bill.getBill_rate(),
      bill.getYear(),
      bill.getBill_amount()
    );
  }
  /*

  public BillingRecordDTO fetchOneBillById(int id) {
    Billing bill = billingRepository.findById((long) id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));

    // Extract LGA name if it exists
    String lgaName = (bill.getLga() != null) ? bill.getLga().getLga_name() : null;

    // Map the Billing entity to BillingRecordDTO
    return BillingRecordDTO.billingRecordBuilder(
      Math.toIntExact(bill.getB_id()),
      bill.getBuilding_code(),
      bill.getAssessment_no(),
      bill.getAssessed_value(),
      bill.getBill_amount(),
      bill.getDate(),
      bill.getPaid(),
      bill.getPaid_amount(),
      bill.getObjection(),
      bill.getYear(),
      lgaName
    );
  }*/


  public List<OutstandingBillDTO> fetchOutstandingBills() {

    // Fetch bills by year and LGA
    List<Billing> bills = billingRepository.findOutstandingBillsByPaymentStatus(0, 0);

    // Return an empty list if no records are found
    if (bills.isEmpty()) {
      return Collections.emptyList();
    }
    // Map Billing entities to BillingRecordDTOs
    return bills.stream().map(bill -> {
      String lgaName = bill.getLga() != null ? bill.getLga().getLga_name() : null;
      return OutstandingBillDTO.outstandingBuilder(
        Math.toIntExact(bill.getB_id()),
        bill.getAssessment_no(),
        bill.getBuilding_code(),
        bill.getYear(),
        bill.getPropertyList().getZoneName(),
        bill.getPropertyList().getPropertyClassification().getName(),
        bill.getPropertyList().getOwnerName(),
        bill.getBill_amount(),
        0.00,
        lgaName
      );
    }).collect(Collectors.toList());
  }









}
