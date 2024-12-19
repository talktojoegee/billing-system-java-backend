package com.billing.system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "dtoBuilder")
public class OwnersRecordDTO {

    private Long id;
    //private Long lga_id;
    private String lgaName;
    private String kgtin;
    private String name;
    private String telephone;
    private String email;
    private String resAddress;

}
