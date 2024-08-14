package no.obrien.fakebank.exception;

/***
 * Thrown to indicate the there are insufficient funds in an account
 */
public class InsufficientFundsException extends RuntimeException {

  /***
   * Constructs an instance of InsufficientFundsException
   */
  public InsufficientFundsException() {
    super("Insufficient funds");
  }
}
