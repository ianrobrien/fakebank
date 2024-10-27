package dev.ianrobrien.fakebank.exception;

/***
 * Thrown to indicate that the given account is invalid
 */
public class InvalidAccountException extends RuntimeException {

  /***
   * Constructs an instance of InvalidAccountException
   */
  public InvalidAccountException() {
    super("Invalid account details");
  }
}
