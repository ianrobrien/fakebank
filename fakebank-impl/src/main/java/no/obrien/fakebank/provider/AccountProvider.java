package no.obrien.fakebank.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.repository.AccountRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/***
 * An implementation of AccountProvider which fetches accounts from a repository
 */
@Component
@RequiredArgsConstructor
@Profile({"dev", "default"})
@Slf4j
public class AccountProvider {

  private final AccountRepository accountRepository;

  /***
   * Gets an account from the given account id
   * @param accountId the account id of the requested account
   * @return the requested account
   * @throws InvalidAccountException when the account id is invalid
   */
  public Account getAccount(String accountId) throws InvalidAccountException {
    log.info("Getting account from account provider with id {}", accountId);
    return accountRepository.getAccount(accountId);
  }
}
