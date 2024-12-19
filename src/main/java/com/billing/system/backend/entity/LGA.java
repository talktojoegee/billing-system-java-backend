package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LGA {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String lga_name;


  @OneToMany(mappedBy = "lga", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Owners> owners;

  @OneToMany(mappedBy = "lga", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Accounts> accounts;


  @OneToMany(mappedBy = "lga", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AreaOffice> areaOffices;

  @OneToMany(mappedBy = "lga", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Billing> billings;

}
