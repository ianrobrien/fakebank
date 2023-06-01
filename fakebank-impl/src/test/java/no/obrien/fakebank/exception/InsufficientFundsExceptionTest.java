package no.obrien.fakebank.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.InstructedAmount;
import no.obrien.fakebank.provider.AccountProvider;
import no.obrien.fakebank.provider.PaymentProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/***
 * Tests the insufficient funds exception
 */
@SpringBootTest
public class InsufficientFundsExceptionTest {

  /***
   * Verifies that initiating a payment with insufficient funds throws
   * an insufficient funds exception
   */
  @Test
  void initiatePayment_insufficientFunds_throwsException() {
    var accountProvider = mock(AccountProvider.class);

    try {
      when(accountProvider.getAccount(anyLong())).thenReturn(Account.builder().build());
    } catch (InvalidAccountException e) {
      throw new RuntimeException(e);
    }

    var paymentProvider = new PaymentProvider(accountProvider);

    var exception =
        assertThrows(
            InsufficientFundsException.class,
            () ->
                paymentProvider.initiatePayment(
                    111L, 222L, new InstructedAmount().amount("100")));

    assertEquals("Insufficient funds", exception.getMessage());
  }
}
