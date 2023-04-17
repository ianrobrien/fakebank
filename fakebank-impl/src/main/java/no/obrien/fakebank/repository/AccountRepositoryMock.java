package no.obrien.fakebank.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.model.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/***
 * Mock repository class that returns a static set of accounts.
 * These accounts are returned by reference such that their values may be
 * changed during runtime.
 */
@Component
@RequiredArgsConstructor
@Profile({"dev", "default"})
@Slf4j
public class AccountRepositoryMock implements AccountRepository {

  // The initial accounts to be returned by the mock repository
  private final List<Account> accounts =
      List.of(
          Account.builder()
              .id("111")
              .currency("GBP")
              .balance(100)
              .owner("Ian Robert O'Brien")
              .build(),
          Account.builder()
              .id("222")
              .currency("GBP")
              .balance(200)
              .owner("Jenny Wold O'Brien")
              .build());

  /***
   * Returns the given account
   * @param accountId the id of the requested account
   * @return the given account
   * @throws InvalidAccountException when the id is invalid or the account is not found
   */
  @Override
  public Account getAccount(String accountId) throws InvalidAccountException {
    log.info("Fetching account from repository with id {}", accountId);
    return accounts.stream()
        .filter(a -> a.getId().equals(accountId))
        .findFirst()
        .orElseThrow(InvalidAccountException::new);
  }
}
