package no.obrien.fakebank.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.InstructedAmount;
import no.obrien.fakebank.service.AccountService;
import no.obrien.fakebank.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/***
 * Tests the invalid payment request exception
 */
@ExtendWith(SpringExtension.class)
public class InvalidPaymentRequestExceptionTest {

  /***
   * Verifies that requesting a payment with an invalid account throws
   * an invalid account exception
   * @throws InvalidAccountException
   */
  @Test()
  void initiatePayment_invalidAccount_throwsInvalidAccountException()
      throws InvalidAccountException {
    var accountService = mock(AccountService.class);
    var paymentService = new PaymentService(accountService);

    when(accountService.getAccount(anyLong())).thenReturn(Account.builder().build());

    var exception =
        assertThrows(
            InvalidPaymentRequestException.class,
            () ->
                paymentService.initiatePayment(
                    1L, 2L, new InstructedAmount().amount("-10.0")));

    assertEquals("Invalid payment request", exception.getMessage());
  }
}
