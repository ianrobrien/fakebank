package no.obrien.fakebank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import no.obrien.fakebank.model.GeneralError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class ControllerExceptionHandlerTest {

  @Autowired
  private ControllerExceptionHandler controllerExceptionHandler;

  /***
   * Verifies that status code and message of an invalid account exception
   */
  @Test
  public void invalidAccountException_verifyStatusCodeAndMessage() {
    var exception = controllerExceptionHandler.handleInvalidAccountException();
    assertNotNull(exception.getBody());
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    assertEquals("Account not found", exception.getBody().getMessage());
  }

  /***
   * Verifies that status code and message of an insufficient funds exception
   */
  @Test
  public void insufficientFundsException_verifyStatusCodeAndMessage() {
    var exception = controllerExceptionHandler.handleInsufficientFundsException();
    assertNotNull(exception.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    assertEquals("Insufficient funds", exception.getBody().getMessage());
  }

  /***
   * Verifies that status code and message of an invalid payment request exception
   */
  @Test
  public void invalidPaymentRequestException_verifyStatusCodeAndMessage() {
    var exception = controllerExceptionHandler.handleInvalidPaymentRequest();
    assertNotNull(exception.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    assertEquals("Invalid payment request", exception.getBody().getMessage());
  }
}
