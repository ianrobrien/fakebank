package no.obrien.fakebank.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import no.obrien.fakebank.exception.InsufficientFundsException;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.exception.InvalidPaymentRequestException;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.InstructedAmount;
import no.obrien.fakebank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/***
 * Tests the payment provider
 */
@ExtendWith(MockitoExtension.class)
public class PaymentProviderMockTest {

  /***
   * Verify that initiating a payment with an invalid account id throws and invalid account
   * exception
   */
  @Test
  void initiatePayment_invalidAccount_throwsException() {
    var accountRepository = mock(AccountRepository.class);
    when(accountRepository.findById(anyLong())).thenThrow(new InvalidAccountException());

    var paymentProvider = new PaymentProvider(new AccountProvider(accountRepository));

    assertThrows(
        InvalidAccountException.class,
        () -> paymentProvider.initiatePayment(-1L, -1L, new InstructedAmount()));
  }

  /***
   * Verify that initiating a valid payment results in the creditor being credited and the
   * debtor being debited
   * @throws InvalidAccountException
   */
  @Test
  void initiatePayment_validRequest_updatesBalance()
      throws InvalidAccountException, InsufficientFundsException, InvalidPaymentRequestException {
    var debtorId = 111L;
    var creditorId = 222L;

    var debtor = Account.builder().balance(100).id(debtorId).build();
    var creditor = Account.builder().balance(100).id(creditorId).build();

    var accountProvider = mock(AccountProvider.class);
    when(accountProvider.getAccount(debtorId)).thenReturn(debtor);
    when(accountProvider.getAccount(creditorId)).thenReturn(creditor);

    var paymentProvider = new PaymentProvider(accountProvider);
    paymentProvider.initiatePayment(
        creditorId, debtorId, new InstructedAmount().amount("10").currency("GBP"));

    assertEquals(90, debtor.getBalance());
    assertEquals(110, creditor.getBalance());
  }
}
