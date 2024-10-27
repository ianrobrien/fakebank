package dev.ianrobrien.fakebank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.ianrobrien.fakebank.mapper.AccountMapper;
import dev.ianrobrien.fakebank.model.Account;
import dev.ianrobrien.fakebank.repository.AccountRepositoryMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AccountMapper.class})
class AccountServiceTest {

  @MockBean
  private AccountMapper accountMapper;

  @Test
  void getAccount() {
    var accountService = new AccountService(new AccountRepositoryMock(), accountMapper);
    assertEquals(
        "Ian Robert",
        accountService.getAccount(1L).getOwner().getFirstName());
  }

  @Test
  void saveAccount() {
    var accountService = new AccountService(new AccountRepositoryMock(), accountMapper);
    assertThrows(UnsupportedOperationException.class,
        () -> accountService.saveAccount(new Account()));
  }
}
