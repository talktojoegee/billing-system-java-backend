package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnersCreateDTO {

  private Long id;
  private Long lga_id;
  private String kgtin;
  private String name;
  private String telephone;
  private String email;
  private String resAddress;
}
