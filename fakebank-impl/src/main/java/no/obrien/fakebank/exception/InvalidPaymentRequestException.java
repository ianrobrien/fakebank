package no.obrien.fakebank.exception;

/***
 * Thrown to indicate the payment request is invalid
 */
public class InvalidPaymentRequestException extends Exception {

  /***
   * Constructs an instance of InvalidPaymentRequestException
   */
  public InvalidPaymentRequestException() {
    super("Invalid payment request");
  }
}
