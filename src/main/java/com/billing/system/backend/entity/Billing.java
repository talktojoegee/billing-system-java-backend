package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "billings")
@NoArgsConstructor
@AllArgsConstructor(staticName = "billingsBuilder")
public class Billing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long b_id;
  private String building_code;
  private String assessment_no;
  private Double assessed_value;
  private Double bill_amount;
  private Integer year;
  private String date;
  private String billed_by;
  @Comment("0=Not paid, 1=paid")
  @Column(name = "paid", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
  private Integer paid;
  private Double paid_amount;
  private String objection;
  private Double bill_rate;
  private String pav_code;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "property_id", insertable = true, updatable = false, nullable = false)
  private PropertyList propertyList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lga_id", insertable = true, updatable = false, nullable = false)
  private LGA lga;
}
