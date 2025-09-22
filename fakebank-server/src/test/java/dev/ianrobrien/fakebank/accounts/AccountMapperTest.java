package dev.ianrobrien.fakebank.accounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.text.NumberFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.SneakyThrows;

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
        .accountOwner(null).build();

    var accountDetails = accountMapper.toAccountDetails(account);
    assertNull(accountDetails.getOwnerId());
  }

  @Test
  void mapAccountDetails_nullOwnerId() {
    var balance = 100.00;
    var currency = "NOK";
    var id = 12345L;

    var owner = AccountOwner.builder()
        .firstName("first")
        .lastName("last")
        .build();

    var account = Account.builder()
        .balance(balance)
        .currency(currency)
        .id(id)
        .accountOwner(owner).build();

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
  @SneakyThrows
  void mapAccountBalance_validInput_validOutput() {
    assertNotNull(accountMapper);

    var balance = 100.00;
    var currency = "NOK";
    var id = 12345L;

    var account = Account.builder().balance(balance).currency(currency).id(id).build();

    var accountBalance = accountMapper.toAccountBalance(account);

    var format = NumberFormat.getInstance(Locale.US);
    assertEquals(balance, format.parse(accountBalance.getBalance()).doubleValue());
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
  @SneakyThrows
  void mapAccountDetails_validInput_validOutput() {
    assertNotNull(accountMapper);

    var balance = 100.00;
    var currency = "NOK";
    var id = 12345L;
    var ownerId = 1L;

    var owner = mock(AccountOwner.class);
    given(owner.getId()).willReturn(ownerId);

    var account = Account.builder().balance(balance).currency(currency).id(id).accountOwner(owner).build();

    var accountDetails = accountMapper.toAccountDetails(account);

    var format = NumberFormat.getInstance(Locale.US);
    assertEquals(balance, format.parse(accountDetails.getBalance()).doubleValue());
    assertEquals(currency, accountDetails.getCurrency());
    assertEquals(id, accountDetails.getAccountId());
    assertEquals(ownerId, accountDetails.getOwnerId());
  }
}
