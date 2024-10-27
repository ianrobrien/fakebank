package dev.ianrobrien.fakebank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import dev.ianrobrien.fakebank.mapper.AccountMapper;
import dev.ianrobrien.fakebank.model.Account;
import dev.ianrobrien.fakebank.model.Owner;
import dev.ianrobrien.fakebank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/***
 * Tests the account provider
 */
@ContextConfiguration(classes = {AccountMapper.class})
public class AccountServiceMockTest {

  @MockBean
  private AccountMapper accountMapper;

  /***
   * Verifies that a valid account request returns the valid account
   */
  @Test
  void getAccount_validInput_validOutput() {
    var id = 1L;
    var balance = 0.0;
    var currency = "NOK";

    var owner = Owner.builder().firstName("Ian Robert").lastName("O'Brien").build();

    var accountRepository = mock(AccountRepository.class);
    given(accountRepository.findById(id))
        .willReturn(
            Optional.of(Account.builder()
                .id(id)
                .balance(balance)
                .currency(currency)
                .owner(owner).build()));

    var accountService = new AccountService(accountRepository, accountMapper);

    var account = accountService.getAccount(id);
    assertEquals(id, account.getId());
    assertEquals(balance, account.getBalance());
    assertEquals(currency, account.getCurrency());
  }
}
