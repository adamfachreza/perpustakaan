package com.mandiri.perpustakaan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_user")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "user_id")
  private String id;

  private String name;

  private String address;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phoneNumber;
}
