package no.obrien.fakebank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.obrien.fakebank.exception.InsufficientFundsException;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.exception.InvalidPaymentRequestException;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.InstructedAmount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * Provides payment requests
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

  private final AccountService accountService;

  /***
   * Initiates a payment with the given request
   * @param creditorAccountId the source of the amount
   * @param debtorAccountId the destination of the amount
   * @param instructedAmount the amount to be sent
   */
  @Transactional
  public void initiatePayment(
      Long creditorAccountId, Long debtorAccountId, InstructedAmount instructedAmount)
      throws InsufficientFundsException, InvalidAccountException, InvalidPaymentRequestException {
    log.info("Initiate payment from PaymentService");

    Account creditorAccount = accountService.getAccount(creditorAccountId);
    Account debtorAccount = accountService.getAccount(debtorAccountId);

    var amount = Double.parseDouble(instructedAmount.getAmount());
    validatePaymentRequest(debtorAccount, amount);

    creditorAccount.setBalance(creditorAccount.getBalance() + amount);
    debtorAccount.setBalance(debtorAccount.getBalance() - amount);

    accountService.saveAccount(creditorAccount);
    accountService.saveAccount(debtorAccount);
  }

  /***
   * Validates the payment request (can be expanded later with more business logic)
   * @param debtorAccount the account that will be debited
   * @param amount the amount to debit
   * @throws InvalidPaymentRequestException
   * @throws InsufficientFundsException
   */
  private static void validatePaymentRequest(Account debtorAccount, double amount)
      throws InvalidPaymentRequestException, InsufficientFundsException {
    log.info("Validating payment request");
    if (amount <= 0) {
      throw new InvalidPaymentRequestException();
    }
    if (amount > debtorAccount.getBalance()) {
      throw new InsufficientFundsException();
    }
  }
}
