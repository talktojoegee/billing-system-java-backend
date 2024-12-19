package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "listBuilder")
public class PropertyListRecordDTO {

  private String buildingCode;
  private String address;
  private String area;
  private String size;
  private String image;

  private String lgaName;
  private String title;
  private String owner;
  private String pavCode;

}
