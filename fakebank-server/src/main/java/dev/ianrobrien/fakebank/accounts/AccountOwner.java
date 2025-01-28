package dev.ianrobrien.fakebank.accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * Represent an Owner entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class AccountOwner {

  /***
   * The account ID (e.g. 1111)
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /***
   * The account owner first name (e.g. Ian Robert)
   */
  @Setter
  private String firstName;

  /***
   * The account owner last name (e.g. O'Brien)
   */
  @Setter
  private String lastName;
}
