package no.obrien.fakebank.provider;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.repository.AccountRepository;

/***
 * An implementation of AccountProvider which fetches accounts from a repository
 */
@RequiredArgsConstructor
@Slf4j
public class AccountProvider {

  private final AccountRepository accountRepository;

  /***
   * Gets an account from the given account id
   * @param accountId the account id of the requested account
   * @return the requested account
   * @throws InvalidAccountException when the account id is invalid
   */
  public Account getAccount(Long accountId) throws InvalidAccountException {
    log.info("Getting account from account provider with id {}", accountId);
    return Optional.of(accountRepository.findById(accountId))
        .get()
        .orElseThrow(InvalidAccountException::new);
  }
}
