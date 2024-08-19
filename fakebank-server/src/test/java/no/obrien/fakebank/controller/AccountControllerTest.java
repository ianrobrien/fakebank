package no.obrien.fakebank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.mapper.AccountMapper;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.AccountBalance;
import no.obrien.fakebank.model.AccountDetails;
import no.obrien.fakebank.model.Owner;
import no.obrien.fakebank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/***
 * Tests the account controller
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AccountMapper.class})
class AccountControllerTest {

  @MockBean
  private AccountMapper accountMapper;

  /***
   * Verifies that a valid account returns a valid balance
   */
  @Test
  void getBalance_validAccount_returnsValidData() {
    var accountService = mock(AccountService.class);
    var owner = mock(Owner.class);
    when(owner.getFirstName()).thenReturn("Ian Robert");
    when(owner.getLastName()).thenReturn("O'Brien");

    when(accountService.getAccount(anyLong()))
        .thenReturn(
            Account.builder().id(4444L).balance(0.0).currency("NOK").owner(owner).build());

    given(accountMapper.toAccountBalance(any(Account.class)))
        .willReturn(new AccountBalance().accountId(4444L));
    var accountController = new AccountController(accountService, accountMapper);

    var accountBalance = accountController.getAccountBalance(4444L);

    assertNotNull(accountBalance.getBody());
    assertEquals(HttpStatus.OK, accountBalance.getStatusCode());
    assertEquals(4444, accountBalance.getBody().getAccountId());
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void getBalance_invalidAccount_throwsException() throws InvalidAccountException {
    var accountService = mock(AccountService.class);

    when(accountService.getAccount(anyLong())).thenThrow(new InvalidAccountException());

    var accountController = new AccountController(accountService, accountMapper);

    assertThrows(InvalidAccountException.class, () -> accountController.getAccountBalance(-1L));
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void getAccountDetails_validAccount_returnsValidData() {
    var accountService = mock(AccountService.class);
    var owner = mock(Owner.class);
    when(owner.getFirstName()).thenReturn("Ian Robert");
    when(owner.getLastName()).thenReturn("O'Brien");

    when(accountService.getAccount(anyLong()))
        .thenReturn(
            Account.builder().id(4444L).balance(0.0).currency("NOK").owner(owner).build());

    given(accountMapper.toAccountDetails(any(Account.class)))
        .willReturn(new AccountDetails().accountId(4444L));
    var accountController = new AccountController(accountService, accountMapper);

    var accountDetails = accountController.getAccountDetails(4444L);

    assertNotNull(accountDetails.getBody());
    assertEquals(HttpStatus.OK, accountDetails.getStatusCode());
    assertEquals(4444, accountDetails.getBody().getAccountId());
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void getAccountDetails_invalidAccount_throwsException() throws InvalidAccountException {
    var accountService = mock(AccountService.class);

    when(accountService.getAccount(anyLong())).thenThrow(new InvalidAccountException());

    var accountController = new AccountController(accountService, accountMapper);

    assertThrows(InvalidAccountException.class, () -> accountController.getAccountDetails(-1L));
  }
}
