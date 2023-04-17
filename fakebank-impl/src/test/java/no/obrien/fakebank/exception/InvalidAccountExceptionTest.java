package no.obrien.fakebank.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.obrien.fakebank.repository.AccountRepositoryMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidAccountExceptionTest {

  /***
   * Verifies that fetching an invalid account throws an invalid account
   * exception
   */
  @Test()
  void getAccount_invalidId_throwsInvalidAccountException() {
    var accountRepositoryMock = new AccountRepositoryMock();

    var exception =
        assertThrows(
            InvalidAccountException.class,
            () -> accountRepositoryMock.getAccount("not an account"));
    assertEquals("Invalid account details", exception.getMessage());
  }
}
