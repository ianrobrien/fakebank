package dev.ianrobrien.fakebank.accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * The internal representation of an Account persisted in the system
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Account {

  /***
   * The account ID (e.g. 1111)
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private AccountOwner accountOwner;

  /***
   * The account balance (e.g. 256.16)
   */
  @Setter
  private double balance;

  /***
   * The account currency (e.g. USD)
   */
  @Setter
  private String currency;
}
