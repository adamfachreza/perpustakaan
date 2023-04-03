package com.mandiri.perpustakaan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trx_loan")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Loan {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "loan_id")
  private String id;
  private LocalDate loanDate;

  private LocalDate endDate;

  private LocalDate returnDate;

  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
