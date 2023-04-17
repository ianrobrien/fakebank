package no.obrien.fakebank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/***
 * The internal representation of an Account persisted in the system
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account {

  /***
   * The account ID (e.g. 1111)
   */
  private final String id;

  /***
   * The account owner (e.g. Ian Robert O'Brien)
   */
  private final String owner;

  /***
   * The account balance (e.g. 256.16)
   */
  @Setter private double balance;

  /***
   * The account currency (e.g. USD)
   */
  @Setter private String currency;
}
