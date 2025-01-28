package dev.ianrobrien.fakebank.accounts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/***
 * Tests the invalid account exception
 */
@ExtendWith(SpringExtension.class)
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
