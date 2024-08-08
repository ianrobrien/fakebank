package no.obrien.fakebank.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.repository.AccountRepositoryMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AccountProviderTest {

  @Test
  void getAccount() {
    var accountProvider = new AccountProvider(new AccountRepositoryMock());
    assertEquals(
        "Ian Robert",
        accountProvider.getAccount(1L).getOwner().getFirstName());
  }

  @Test
  void saveAccount() {
    var accountProvider = new AccountProvider(new AccountRepositoryMock());
    assertThrows(UnsupportedOperationException.class,
        () -> accountProvider.saveAccount(new Account()));
  }
}
