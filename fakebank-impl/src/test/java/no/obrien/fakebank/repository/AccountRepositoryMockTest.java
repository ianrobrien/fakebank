package no.obrien.fakebank.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/***
 * Tests the account repository mock
 */
@SpringBootTest
public class AccountRepositoryMockTest {

  private final AccountRepositoryMock accountRepositoryMock = new AccountRepositoryMock();

  /***
   * Verifies that fetching an account with an invalid id will throw
   * an invalid account exception
   */
  @Test
  void getAccount_invalidAccountId_returnsNoAccount() {
    var account = accountRepositoryMock.findById(-1L);
    assertTrue(account.isEmpty());
  }
}
