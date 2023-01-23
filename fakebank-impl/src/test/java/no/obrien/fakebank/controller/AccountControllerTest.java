package no.obrien.fakebank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.mapper.AccountMapper;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.provider.AccountProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class AccountControllerTest {

  @Autowired
  private AccountMapper accountMapper;

  /***
   * Verifies that a valid account returns a valid balance
   */
  @Test
  void getBalance_validAccount_returnsValidData() {
    var accountProvider = mock(AccountProvider.class);
    try {
      when(accountProvider.getAccount(anyString()))
          .thenReturn(Account.builder()
              .id("4444")
              .balance(0.0)
              .currency("NOK")
              .owner("Ian")
              .build());
    } catch (InvalidAccountException e) {
      throw new RuntimeException(e);
    }

    var accountController = new AccountController(accountProvider, accountMapper);

    var accountBalance = accountController.getAccountBalance("4444");

    assertNotNull(accountBalance.getBody());
    assertEquals(HttpStatus.OK, accountBalance.getStatusCode());
    assertEquals("4444", accountBalance.getBody().getAccountId());
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void getBalance_invalidAccount_throwsException() throws InvalidAccountException {
    var accountProvider = mock(AccountProvider.class);

    when(accountProvider.getAccount(anyString()))
        .thenThrow(new InvalidAccountException());

    var accountController = new AccountController(accountProvider, accountMapper);

    assertThrows(InvalidAccountException.class, () ->
        accountController.getAccountBalance(""));
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void getAccountDetails_validAccount_returnsValidData() {
    var accountProvider = mock(AccountProvider.class);
    try {
      when(accountProvider.getAccount(anyString()))
          .thenReturn(Account.builder()
              .id("4444")
              .balance(0.0)
              .currency("NOK")
              .owner("Ian")
              .build());
    } catch (InvalidAccountException e) {
      throw new RuntimeException(e);
    }

    var accountController = new AccountController(accountProvider, accountMapper);

    var accountDetails = accountController.getAccountDetails("4444");

    assertNotNull(accountDetails.getBody());
    assertEquals(HttpStatus.OK, accountDetails.getStatusCode());
    assertEquals("4444", accountDetails.getBody().getAccountId());
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void getAccountDetails_invalidAccount_throwsException() throws InvalidAccountException {
    var accountProvider = mock(AccountProvider.class);

    when(accountProvider.getAccount(anyString()))
        .thenThrow(new InvalidAccountException());

    var accountController = new AccountController(accountProvider, accountMapper);

    assertThrows(InvalidAccountException.class, () ->
        accountController.getAccountDetails(""));
  }
}
