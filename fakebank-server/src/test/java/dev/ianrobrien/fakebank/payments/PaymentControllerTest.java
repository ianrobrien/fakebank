package dev.ianrobrien.fakebank.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import dev.ianrobrien.fakebank.accounts.AccountOwner;
import java.util.Optional;
import dev.ianrobrien.fakebank.accounts.InvalidAccountException;
import dev.ianrobrien.fakebank.accounts.AccountMapper;
import dev.ianrobrien.fakebank.accounts.Account;
import dev.ianrobrien.fakebank.model.InstructedAmount;
import dev.ianrobrien.fakebank.model.PaymentRequest;
import dev.ianrobrien.fakebank.accounts.AccountRepository;
import dev.ianrobrien.fakebank.accounts.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/***
 * Tests the payment exceptions
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AccountMapper.class})
class PaymentControllerTest {

  @MockitoBean
  private AccountMapper accountMapper;

  /***
   * Verifies that a payment with a negative amount throws an exception
   */
  @Test
  void initiatePayment_negativePayment_throwsException() {
    var accountRepository = mock(AccountRepository.class);
    given(accountRepository.findById(anyLong())).willReturn(
        Optional.of(Account.builder().build()));

    var accountService = new AccountService(accountRepository, accountMapper);
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
   */
  @Test
  void initiatePayment_invalidAccount_throwsException() {
    var accountService = mock(AccountService.class);
    var paymentService = new PaymentService(accountService);

    given(accountService.getAccount(anyLong())).willThrow(new InvalidAccountException());

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
   */
  @Test
  void initiatePayment_insufficientFunds_throwsException() {
    var accountService = mock(AccountService.class);
    var paymentService = new PaymentService(accountService);

    var owner = AccountOwner.builder().firstName("Ian Robert").lastName("O'Brien").build();
    given(accountService.getAccount(anyLong()))
        .willReturn(
            Account.builder().id(1L).balance(0.0).currency("NOK").accountOwner(owner).build());

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
  void initiatePayment_validRequest_success() {
    var accountService = mock(AccountService.class);
    var paymentService = mock(PaymentService.class);
    var owner = AccountOwner.builder().firstName("Ian Robert").lastName("O'Brien").build();

    given(accountService.getAccount(anyLong()))
        .willReturn(
            Account.builder().id(1L).balance(0.0).currency("NOK").accountOwner(owner).build());
    given(accountService.getAccount(anyLong()))
        .willReturn(
            Account.builder().id(2L).balance(0.0).currency("NOK").accountOwner(owner).build());

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
