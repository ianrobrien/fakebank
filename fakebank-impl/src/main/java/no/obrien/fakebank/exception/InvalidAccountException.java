package no.obrien.fakebank.exception;

/***
 * Thrown to indicate that the given account is invalid
 */
public class InvalidAccountException extends Exception {

  /***
   * Constructs an instance of InvalidAccountException
   */
  public InvalidAccountException() {
    super("Invalid account details");
  }
}
