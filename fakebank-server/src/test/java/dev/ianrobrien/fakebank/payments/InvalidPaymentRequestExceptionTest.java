package dev.ianrobrien.fakebank.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import dev.ianrobrien.fakebank.accounts.Account;
import dev.ianrobrien.fakebank.model.InstructedAmount;
import dev.ianrobrien.fakebank.accounts.AccountService;
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
   */
  @Test()
  void initiatePayment_invalidAccount_throwsInvalidAccountException() {
    var accountService = mock(AccountService.class);
    var paymentService = new PaymentService(accountService);

    given(accountService.getAccount(anyLong())).willReturn(Account.builder().build());

    var exception =
        assertThrows(
            InvalidPaymentRequestException.class,
            () ->
                paymentService.initiatePayment(
                    1L, 2L, new InstructedAmount().amount("-10.0")));

    assertEquals("Invalid payment request", exception.getMessage());
  }
}
