package dev.ianrobrien.fakebank.accounts;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import dev.ianrobrien.fakebank.api.AccountsApi;
import dev.ianrobrien.fakebank.model.AccountBalance;
import dev.ianrobrien.fakebank.model.AccountDetails;
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

  private final AccountService accountService;

  /***
   * Returns the balance of an account given by its id
   * @param accountId  (required)
   * @return the specified account balance
   */
  @SneakyThrows
  @Override
  public ResponseEntity<AccountBalance> getAccountBalance(Long accountId) {
    log.info("Received getAccountBalance request for account {}", accountId);
    return ResponseEntity.ok(accountService.getAccountBalance(accountId));
  }

  /***
   * Returns the details of an account given by its id
   * @param accountId  (required)
   * @return the specified account details
   */
  @SneakyThrows
  @Override
  public ResponseEntity<AccountDetails> getAccountDetails(Long accountId) {
    log.info("Received getAccountDetails request for account {}", accountId);
    return ResponseEntity.ok(accountService.getAccountDetails(accountId));
  }
}
