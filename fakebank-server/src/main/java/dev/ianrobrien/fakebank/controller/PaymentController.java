package dev.ianrobrien.fakebank.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import dev.ianrobrien.fakebank.api.PaymentsApi;
import dev.ianrobrien.fakebank.model.PaymentRequest;
import dev.ianrobrien.fakebank.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/***
 * Handles HTTP requests, can be useful for validating requests and performing
 * common actions such as reading data from headers or adding a request id
 */
@RestController
@AllArgsConstructor
@Slf4j
public class PaymentController implements PaymentsApi {

  private final PaymentService paymentService;

  /***
   * Initiates a payment between two accounts
   * @param paymentRequest the details of the payment request
   * @return OK if the payment is successful
   */
  @SneakyThrows
  @Override
  public ResponseEntity<Void> initiatePayment(PaymentRequest paymentRequest) {
    log.info("Received initiatePayment request");

    paymentService.initiatePayment(
        paymentRequest.getCreditorAccount(),
        paymentRequest.getDebtorAccount(),
        paymentRequest.getInstructedAmount());

    return ResponseEntity.ok().build();
  }
}
