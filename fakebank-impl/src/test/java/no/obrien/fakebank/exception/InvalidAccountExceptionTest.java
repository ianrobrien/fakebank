package no.obrien.fakebank.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import no.obrien.fakebank.repository.AccountRepositoryMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/***
 * Tests the invalid account exception
 */
@SpringBootTest
public class InvalidAccountExceptionTest {

  /***
   * Verifies that fetching an invalid account throws an invalid account
   * exception
   */
  @Test()
  void getAccount_invalidId_returnsNoResult() {
    var accountRepositoryMock = new AccountRepositoryMock();
    var account = accountRepositoryMock.findById(-1L);
    assertTrue(account.isEmpty());
  }
}
