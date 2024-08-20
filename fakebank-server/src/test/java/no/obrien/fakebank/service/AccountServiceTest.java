package no.obrien.fakebank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.obrien.fakebank.mapper.AccountMapper;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.repository.AccountRepositoryMock;
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
