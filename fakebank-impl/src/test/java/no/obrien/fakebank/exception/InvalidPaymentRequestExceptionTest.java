package no.obrien.fakebank.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.InstructedAmount;
import no.obrien.fakebank.provider.AccountProvider;
import no.obrien.fakebank.provider.PaymentProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidPaymentRequestExceptionTest {

  /***
   * Verifies that requesting a payment with an invalid account throws
   * an invalid account exception
   * @throws InvalidAccountException
   */
  @Test()
  void initiatePayment_invalidAccount_throwsInvalidAccountException()
      throws InvalidAccountException {
    var accountProvider = mock(AccountProvider.class);
    var paymentProvider = new PaymentProvider(accountProvider);

    when(accountProvider.getAccount(anyString())).thenReturn(Account.builder().build());

    var exception =
        assertThrows(
            InvalidPaymentRequestException.class,
            () ->
                paymentProvider.initiatePayment(
                    "111", "222", new InstructedAmount().amount("-10.0")));

    assertEquals("Invalid payment request", exception.getMessage());
  }
}
