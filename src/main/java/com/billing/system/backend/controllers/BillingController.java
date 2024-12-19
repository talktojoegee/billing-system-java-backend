package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.*;
import com.billing.system.backend.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/billing")
public class BillingController {


  private final BillingService billingService;

  @Autowired
  public BillingController(BillingService billingService) {
    this.billingService = billingService;
  }

/*  @PostMapping("/process")
  public ResponseEntity<Map<String, String>> processBilling(@RequestBody @Valid BillingCreateDTO dto){

     billingService.storeBill(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Bill processed!"));
  }*/
@PostMapping("/process")
public ResponseEntity<Map<String, String>> processBilling(@RequestBody @Valid BillingCreateDTO dto) {
  try {
    billingService.storeBill(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Bill processed successfully!"));
  } catch (IllegalStateException e) {
    // Handle the case where the bill already exists
    return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()) );
  } catch (IllegalArgumentException e) {
    // Handle the case where there is nothing to process
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
  }
}


  @PostMapping("/retrieve")
  public ResponseEntity<?> retrieveBills(@RequestBody @Valid BillRetrieveCreateDTO dto){
    List<BillingRetrieveRecordDTO> billingRecordDTOS = billingService.fetchBillSummaryByYearAndLga(dto.getYear(), dto.getLgaId());

    if (billingRecordDTOS.isEmpty()) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "No records found.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    return ResponseEntity.ok( billingRecordDTOS);
  }

  @GetMapping("/detail/{id}")
  public ResponseEntity<?> getBillingById(@PathVariable("id") int id) {
    try {
      BillDetailDTO billingRecord = billingService.fetchOneBillById(id);
      return ResponseEntity.ok(billingRecord);
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new BillingRecordDTO("Error: "));
    }
  }

  @GetMapping("/outstanding-bills")
  public ResponseEntity<?> getOutstandingBills() {
    try {
      List<OutstandingBillDTO> records = billingService.fetchOutstandingBills();

      // If the list is empty, return a 404 status with a meaningful message
      if (records.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Collections.singletonMap("message", "No outstanding bills found"));
      }
      // Return the list of outstanding bills
      return ResponseEntity.ok(records);

    } catch (IllegalArgumentException ex) {
      // 400 status
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Collections.singletonMap("error", "Error: Bad request"));
    } catch (Exception ex) {
      // 500 status
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Collections.singletonMap("error", "An unexpected error occurred: "));
    }
  }

/*  public ResponseEntity<?> getOutstandingBills() {
    try {
      OutstandingBillDTO record =  billingService.fetchOutstandingBills();
      return ResponseEntity.ok(record);
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new BillingRecordDTO("Error: " + ex.getMessage()));
    }
  }*/





}
