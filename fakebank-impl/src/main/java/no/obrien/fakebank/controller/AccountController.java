package no.obrien.fakebank.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import no.obrien.fakebank.api.AccountsApi;
import no.obrien.fakebank.mapper.AccountMapper;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.AccountBalance;
import no.obrien.fakebank.model.AccountDetails;
import no.obrien.fakebank.provider.AccountProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/***
 * Handles HTTP requests, can be useful for validating requests and performing
 * common actions such as reading data from headers or adding a request id
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController implements AccountsApi {

  private final AccountProvider accountProvider;
  private final AccountMapper accountMapper;

  /***
   * Returns the balance of an account given by its id
   * @param accountId  (required)
   * @return the specified account balance
   */
  @SneakyThrows
  @Override
  public ResponseEntity<AccountBalance> getAccountBalance(String accountId) {
    log.info("Received getAccountBalance request for account {}", accountId);
    Account account = accountProvider.getAccount(accountId);
    return ResponseEntity.ok(accountMapper.toAccountBalance(account));
  }

  /***
   * Returns the details of an account given by its id
   * @param accountId  (required)
   * @return the specified account details
   */
  @SneakyThrows
  @Override
  public ResponseEntity<AccountDetails> getAccountDetails(String accountId) {
    log.info("Received getAccountDetails request for account {}", accountId);
    Account account = accountProvider.getAccount(accountId);
    return ResponseEntity.ok(accountMapper.toAccountDetails(account));
  }
}
