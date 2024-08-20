package no.obrien.fakebank.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Tests the account mapper
 */
@ExtendWith(SpringExtension.class)
class AccountMapperTest {

  private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

  @Test
  void mapAccountDetails_nullOwner() {
    var balance = 100.00;
    var currency = "NOK";
    var id = 12345L;

    var account = Account.builder()
        .balance(balance)
        .currency(currency)
        .id(id)
        .owner(null).build();

    var accountDetails = accountMapper.toAccountDetails(account);
    assertNull(accountDetails.getOwnerId());
  }

  @Test
  void mapAccountDetails_nullOwnerId() {
    var balance = 100.00;
    var currency = "NOK";
    var id = 12345L;

    var owner = Owner.builder()
        .firstName("first")
        .lastName("last")
        .build();

    var account = Account.builder()
        .balance(balance)
        .currency(currency)
        .id(id)
        .owner(owner).build();

    var accountDetails = accountMapper.toAccountDetails(account);
    assertNull(accountDetails.getOwnerId());
  }

  /***
   * Verifies that mapping a null instance returns a null result
   */
  @Test
  void mapAccountBalance_nullInput_nullOutput() {
    assertNull(accountMapper.toAccountBalance(null));
  }

  /***
   * Verifies that mapping a valid input returns a valid output
   */
  @Test
  void mapAccountBalance_validInput_validOutput() {
    assertNotNull(accountMapper);

    var balance = 100.00;
    var currency = "NOK";
    var id = 12345L;

    var account = Account.builder().balance(balance).currency(currency).id(id).build();

    var accountBalance = accountMapper.toAccountBalance(account);

    assertEquals(balance, Double.parseDouble(accountBalance.getBalance()));
    assertEquals(currency, accountBalance.getCurrency());
    assertEquals(id, accountBalance.getAccountId());
  }

  /***
   * Verifies that mapping a null instance returns a null result
   */
  @Test
  void mapAccountDetails_nullInput_nullOutput() {
    assertNull(accountMapper.toAccountDetails(null));
  }

  /***
   * Verifies that mapping a valid input returns a valid output
   */
  @Test
  void mapAccountDetails_validInput_validOutput() {
    assertNotNull(accountMapper);

    var balance = 100.00;
    var currency = "NOK";
    var id = 12345L;
    var ownerId = 1L;

    var owner = mock(Owner.class);
    given(owner.getId()).willReturn(ownerId);

    var account = Account.builder().balance(balance).currency(currency).id(id).owner(owner).build();

    var accountDetails = accountMapper.toAccountDetails(account);

    assertEquals(balance, Double.parseDouble(accountDetails.getBalance()));
    assertEquals(currency, accountDetails.getCurrency());
    assertEquals(id, accountDetails.getAccountId());
    assertEquals(ownerId, accountDetails.getOwnerId());
  }
}
