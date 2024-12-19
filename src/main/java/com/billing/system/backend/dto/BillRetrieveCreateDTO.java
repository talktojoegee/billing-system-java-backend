package com.billing.system.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "billingBuilder")

public class BillRetrieveCreateDTO {


    @NotNull(message = "Indicate the year")
    private Integer year; //frontend


    @NotNull(message = "Choose LGA")
    private Integer lgaId; //frontend





}
