package no.obrien.fakebank.controller;

import no.obrien.fakebank.exception.InsufficientFundsException;
import no.obrien.fakebank.exception.InvalidAccountException;
import no.obrien.fakebank.exception.InvalidPaymentRequestException;
import no.obrien.fakebank.model.GeneralError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/***
 * Handles exceptions thrown by the controllers
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  /***
   * Handles invalid account exceptions
   * @return the error response
   */
  @ExceptionHandler(InvalidAccountException.class)
  public ResponseEntity<GeneralError> handleInvalidAccountException() {
    return new ResponseEntity<>(
        new GeneralError().message("Account not found"), HttpStatus.NOT_FOUND);
  }

  /***
   * Handles insufficient funds exceptions
   * @return the error response
   */
  @ExceptionHandler(InsufficientFundsException.class)
  public ResponseEntity<GeneralError> handleInsufficientFundsException() {
    return new ResponseEntity<>(
        new GeneralError().message("Insufficient funds"), HttpStatus.BAD_REQUEST);
  }

  /***
   * Handles invalid payment request exceptions
   * @return the error response
   */
  @ExceptionHandler(InvalidPaymentRequestException.class)
  public ResponseEntity<GeneralError> handleInvalidPaymentRequest() {
    return new ResponseEntity<>(
        new GeneralError().message("Invalid payment request"), HttpStatus.BAD_REQUEST);
  }
}
