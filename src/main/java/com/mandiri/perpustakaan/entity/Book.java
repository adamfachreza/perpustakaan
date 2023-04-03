package com.mandiri.perpustakaan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_book")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "book_id")
  private String id;

  private String title;

  private String author;

  private String publisher;

  private Integer stock;
}
