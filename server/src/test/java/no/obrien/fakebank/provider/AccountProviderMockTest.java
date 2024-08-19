package no.obrien.fakebank.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.Owner;
import no.obrien.fakebank.repository.AccountRepository;
import org.junit.jupiter.api.Test;

/***
 * Tests the account provider
 */
public class AccountProviderMockTest {

  /***
   * Verifies that a valid account request returns the valid account
   * @throws InvalidAccountException
   */
  @Test
  void getAccount_validInput_validOutput() throws InvalidAccountException {
    var id = 1L;
    var balance = 0.0;
    var currency = "NOK";

    var owner = mock(Owner.class);
    when(owner.getFirstName()).thenReturn("Ian Robert");
    when(owner.getLastName()).thenReturn("O'Brien");

    var accountRepository = mock(AccountRepository.class);
    when(accountRepository.findById(id))
        .thenReturn(
            Optional.of(Account.builder()
                .id(id)
                .balance(balance)
                .currency(currency)
                .owner(owner).build()));

    var accountProvider = new AccountProvider(accountRepository);

    var account = accountProvider.getAccount(id);
    assertEquals(id, account.getId());
    assertEquals(balance, account.getBalance());
    assertEquals(currency, account.getCurrency());
  }
}
