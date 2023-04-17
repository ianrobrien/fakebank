package no.obrien.fakebank.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import no.obrien.fakebank.model.Account;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountMapperTest {

  private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

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
    var id = "12345";

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
    var id = "12345";
    var owner = "Ian Robert O'Brien";

    var account = Account.builder().balance(balance).currency(currency).id(id).owner(owner).build();

    var accountDetails = accountMapper.toAccountDetails(account);

    assertEquals(balance, Double.parseDouble(accountDetails.getBalance()));
    assertEquals(currency, accountDetails.getCurrency());
    assertEquals(id, accountDetails.getAccountId());
    assertEquals(owner, accountDetails.getOwnerName());
  }
}
