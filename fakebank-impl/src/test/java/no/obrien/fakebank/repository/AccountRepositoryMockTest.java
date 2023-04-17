package no.obrien.fakebank.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.obrien.fakebank.exception.InvalidAccountException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryMockTest {

  private final AccountRepositoryMock accountRepositoryMock = new AccountRepositoryMock();

  /***
   * Verifies that fetching an account with an invalid id will throw
   * an invalid account exception
   */
  @Test
  void getAccount_invalidAccountId_throwsException() {
    var exception =
        assertThrows(
            InvalidAccountException.class,
            () -> accountRepositoryMock.getAccount("not an account"));

    assertEquals("Invalid account details", exception.getMessage());
  }
}
