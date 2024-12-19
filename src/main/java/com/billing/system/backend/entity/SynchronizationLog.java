package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "synchronization_logs")
@NoArgsConstructor
@AllArgsConstructor
public class SynchronizationLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int gGis;
  private int kLabs;
  private String lastSync;
  private int lga_id;

}
