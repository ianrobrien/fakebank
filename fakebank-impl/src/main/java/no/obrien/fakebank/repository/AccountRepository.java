package no.obrien.fakebank.repository;

import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.model.Account;

/***
 * Specifies the functions that need to be implemented in order
 * to retrieve accounts
 */
public interface AccountRepository {

  /***
   * Gets an account from a store with the given account id
   * @param accountId the id of the account to fetch
   * @return the requested account
   * @throws InvalidAccountException when the id is invalid or the account is not found
   */
  Account getAccount(String accountId) throws InvalidAccountException;
}
