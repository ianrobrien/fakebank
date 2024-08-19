package no.obrien.fakebank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import no.obrien.fakebank.exception.InsufficientFundsException;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.exception.InvalidPaymentRequestException;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.InstructedAmount;
import no.obrien.fakebank.model.Owner;
import no.obrien.fakebank.model.PaymentRequest;
import no.obrien.fakebank.repository.AccountRepository;
import no.obrien.fakebank.service.AccountService;
import no.obrien.fakebank.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/***
 * Tests the payment controller
 */
@ExtendWith(SpringExtension.class)
class PaymentControllerTest {

  /***
   * Verifies that a payment with a negative amount throws an exception
   * @throws InvalidAccountException
   */
  @Test
  void initiatePayment_negativePayment_throwsException() throws InvalidAccountException {
    var accountRepository = mock(AccountRepository.class);
    when(accountRepository.findById(anyLong())).thenReturn(
        Optional.of(Account.builder().build()));

    var accountService = new AccountService(accountRepository);
    var paymentService = new PaymentService(accountService);
    var paymentController = new PaymentController(paymentService);

    assertThrows(
        InvalidPaymentRequestException.class,
        () ->
            paymentController.initiatePayment(
                new PaymentRequest()
                    .creditorAccount(1L)
                    .debtorAccount(1L)
                    .instructedAmount(new InstructedAmount().amount("-10.00"))));
  }

  /***
   * Verifies that a payment with an invalid account throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void initiatePayment_invalidAccount_throwsException() throws InvalidAccountException {
    var accountService = mock(AccountService.class);
    var paymentService = new PaymentService(accountService);

    when(accountService.getAccount(anyLong())).thenThrow(new InvalidAccountException());

    var paymentController = new PaymentController(paymentService);
    assertThrows(
        InvalidAccountException.class,
        () ->
            paymentController.initiatePayment(
                new PaymentRequest()
                    .creditorAccount(1234L)
                    .debtorAccount(5678L)
                    .instructedAmount(new InstructedAmount().amount("100").currency("NOK"))));
  }

  /***
   * Verifies that a payment with an invalid account throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void initiatePayment_insufficientFunds_throwsException() throws InvalidAccountException {
    var accountService = mock(AccountService.class);
    var paymentService = new PaymentService(accountService);

    var owner = mock(Owner.class);

    when(owner.getFirstName()).thenReturn("Ian Robert");
    when(owner.getLastName()).thenReturn("O'Brien");
    when(owner.getId()).thenReturn(1L);

    when(accountService.getAccount(anyLong()))
        .thenReturn(
            Account.builder().id(1L).balance(0.0).currency("NOK").owner(owner).build());

    var paymentController = new PaymentController(paymentService);
    assertThrows(
        InsufficientFundsException.class,
        () ->
            paymentController.initiatePayment(
                new PaymentRequest()
                    .creditorAccount(1234L)
                    .debtorAccount(5678L)
                    .instructedAmount(new InstructedAmount().amount("100").currency("NOK"))));
  }

  /***
   * Verifies that a valid payment request returns a 200 OK response
   */
  @Test
  void initiatePayment_validRequest_success() throws InvalidAccountException {
    var accountService = mock(AccountService.class);
    var paymentService = mock(PaymentService.class);

    var owner = mock(Owner.class);

    when(owner.getFirstName()).thenReturn("Ian Robert");
    when(owner.getLastName()).thenReturn("O'Brien");
    when(owner.getId()).thenReturn(1L);

    when(accountService.getAccount(1L))
        .thenReturn(
            Account.builder().id(1L).balance(0.0).currency("NOK").owner(owner).build());

    when(accountService.getAccount(2L))
        .thenReturn(
            Account.builder().id(2L).balance(0.0).currency("NOK").owner(owner).build());

    var paymentController = new PaymentController(paymentService);
    var response =
        paymentController.initiatePayment(
            new PaymentRequest()
                .creditorAccount(1234L)
                .debtorAccount(5678L)
                .instructedAmount(new InstructedAmount().amount("100").currency("NOK")));

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
