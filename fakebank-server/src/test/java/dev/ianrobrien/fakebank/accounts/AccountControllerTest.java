package dev.ianrobrien.fakebank.accounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/***
 * Tests the account exceptions
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AccountMapperImpl.class})
class AccountControllerTest {

  @Autowired
  private AccountMapper accountMapper;

  /***
   * Verifies that a valid account returns a valid balance
   */
  @Test
  void getBalance_validAccount_returnsValidData() {
    var accountRepository = mock(AccountRepository.class);
    var accountService = new AccountService(accountRepository, accountMapper);

    given(accountRepository
        .findById(anyLong()))
        .willReturn(Optional.of(Account.builder()
            .id(4444L)
            .balance(0.0)
            .currency("NOK")
            .accountOwner(new AccountOwner()).build()));

    var accountController = new AccountController(accountService);

    var accountBalance = accountController.getAccountBalance(4444L);

    assertNotNull(accountBalance.getBody());
    assertEquals(HttpStatus.OK, accountBalance.getStatusCode());
    assertEquals(4444, accountBalance.getBody().getAccountId());
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   */
  @Test
  void getBalance_invalidAccount_throwsException() {
    var accountRepository = mock(AccountRepository.class);
    given(accountRepository.findById(anyLong())).willReturn(Optional.empty());

    var accountService = new AccountService(accountRepository, accountMapper);
    var accountController = new AccountController(accountService);

    assertThrows(InvalidAccountException.class, () -> accountController.getAccountBalance(-1L));
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   */
  @Test
  void getAccountDetails_validAccount_returnsValidData() {
    var accountRepository = mock(AccountRepository.class);
    given(accountRepository.findById(anyLong())).willReturn(
        Optional.of(Account.builder()
            .id(4444L)
            .balance(0.0)
            .currency("NOK")
            .accountOwner(AccountOwner.builder().firstName("Ian Robert").lastName("O'Brien").build())
            .build()));

    var accountService = new AccountService(accountRepository, accountMapper);
    var accountController = new AccountController(accountService);
    var accountDetails = accountController.getAccountDetails(4444L);

    assertNotNull(accountDetails.getBody());
    assertEquals(HttpStatus.OK, accountDetails.getStatusCode());
    assertEquals(4444, accountDetails.getBody().getAccountId());
  }

  /***
   * Verifies that an invalid account id throws an invalid account exception
   */
  @Test
  void getAccountDetails_invalidAccount_throwsException() {
    var accountRepository = mock(AccountRepository.class);
    var accountService = new AccountService(accountRepository, accountMapper);

    given(accountRepository.findById(anyLong())).willReturn(Optional.empty());
    var accountController = new AccountController(accountService);

    assertThrows(InvalidAccountException.class, () -> accountController.getAccountDetails(-1L));
  }
}
