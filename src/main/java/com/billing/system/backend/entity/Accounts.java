package com.billing.system.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName ="buld")
@Table(name = "accounts")
@Data
public class Accounts {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column( nullable = false)
  private String first_name;

  @Column(nullable = false)
  private String last_name;

  @Column(nullable = true)
  private String other_names;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = true)
  private String mobile_no;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String roles;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = true)
  private String title;

  @Column(nullable = true)
  @Comment("1=Active, 0=pending,2=Deactivated")
  private int status = 1;

  @Column(nullable = true)
  private String avatar = "avatar.png"; //profile image

  @Column(nullable = false)
  private String url;

  @Column()
  @Comment("1=male,2=female")
  private int gender = 1;

  @Column()
  private int marital_status;

  @Column()
  private String address;

  @Column()
  private String occupation;


/* @Column()
  private int lga_id;*/

  @Column()
  private String dob;

  @Column()
  private String dob_month;

  @Column()
  private String dob_year;

  @Column()
  private String dob_day;

  /*
    Relationships
  */

  @ManyToOne
  @JoinColumn(name = "lga_id", nullable = false, insertable = false, updatable = false)
  private LGA lga;

}
