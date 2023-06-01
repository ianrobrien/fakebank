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
import no.obrien.fakebank.model.PaymentRequest;
import no.obrien.fakebank.model.User;
import no.obrien.fakebank.provider.AccountProvider;
import no.obrien.fakebank.provider.PaymentProvider;
import no.obrien.fakebank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

/***
 * Tests the payment controller
 */
@SpringBootTest
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

    var accountProvider = new AccountProvider(accountRepository);
    var paymentProvider = new PaymentProvider(accountProvider);
    var paymentController = new PaymentController(paymentProvider);

    assertThrows(
        InvalidPaymentRequestException.class,
        () ->
            paymentController.initiatePayment(
                new PaymentRequest()
                    .creditorAccount(111L)
                    .debtorAccount(111L)
                    .instructedAmount(new InstructedAmount().amount("-10.00"))));
  }

  /***
   * Verifies that a payment with an invalid account throws an invalid account exception
   * @throws InvalidAccountException
   */
  @Test
  void initiatePayment_invalidAccount_throwsException() throws InvalidAccountException {
    var accountProvider = mock(AccountProvider.class);
    var paymentProvider = new PaymentProvider(accountProvider);

    when(accountProvider.getAccount(anyLong())).thenThrow(new InvalidAccountException());

    var paymentController = new PaymentController(paymentProvider);
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
    var accountProvider = mock(AccountProvider.class);
    var paymentProvider = new PaymentProvider(accountProvider);

    var owner = mock(User.class);

    when(owner.getFirstName()).thenReturn("Ian Robert");
    when(owner.getLastName()).thenReturn("O'Brien");
    when(owner.getId()).thenReturn(1L);

    when(accountProvider.getAccount(anyLong()))
        .thenReturn(
            Account.builder().id(111L).balance(0.0).currency("NOK").owner(owner).build());

    var paymentController = new PaymentController(paymentProvider);
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
    var accountProvider = mock(AccountProvider.class);
    var paymentProvider = mock(PaymentProvider.class);

    var owner = mock(User.class);

    when(owner.getFirstName()).thenReturn("Ian Robert");
    when(owner.getLastName()).thenReturn("O'Brien");
    when(owner.getId()).thenReturn(1L);

    when(accountProvider.getAccount(111L))
        .thenReturn(
            Account.builder().id(111L).balance(0.0).currency("NOK").owner(owner).build());

    when(accountProvider.getAccount(222L))
        .thenReturn(
            Account.builder().id(222L).balance(0.0).currency("NOK").owner(owner).build());

    var paymentController = new PaymentController(paymentProvider);
    var response =
        paymentController.initiatePayment(
            new PaymentRequest()
                .creditorAccount(1234L)
                .debtorAccount(5678L)
                .instructedAmount(new InstructedAmount().amount("100").currency("NOK")));

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

//  private User getMockOwner() {
//    var owner = mock(User.class);
//
//    when(owner.getFirstName()).thenReturn("Ian Robert");
//    when(owner.getLastName()).thenReturn("O'Brien");
//    when(owner.getId()).thenReturn(1L);
//
//    return owner;
//  }
}
