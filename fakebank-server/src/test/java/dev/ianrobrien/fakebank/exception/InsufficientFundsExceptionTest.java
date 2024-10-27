package dev.ianrobrien.fakebank.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import dev.ianrobrien.fakebank.model.Account;
import dev.ianrobrien.fakebank.model.InstructedAmount;
import dev.ianrobrien.fakebank.service.AccountService;
import dev.ianrobrien.fakebank.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/***
 * Tests the insufficient funds exception
 */
@ExtendWith(SpringExtension.class)
public class InsufficientFundsExceptionTest {

  /***
   * Verifies that initiating a payment with insufficient funds throws
   * an insufficient funds exception
   */
  @Test
  void initiatePayment_insufficientFunds_throwsException() {
    var accountService = mock(AccountService.class);

    try {
      given(accountService.getAccount(anyLong())).willReturn(Account.builder().build());
    } catch (InvalidAccountException e) {
      throw new RuntimeException(e);
    }

    var paymentService = new PaymentService(accountService);

    var exception =
        assertThrows(
            InsufficientFundsException.class,
            () ->
                paymentService.initiatePayment(
                    1L, 2L, new InstructedAmount().amount("100")));

    assertEquals("Insufficient funds", exception.getMessage());
  }
}
