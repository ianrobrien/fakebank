package no.obrien.fakebank.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

  @Test
  void save() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.save(null));
  }

  @Test
  void saveAll() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.saveAll(null));
  }

  @Test
  void existsById() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.existsById(null));
  }

  @Test
  void findAll() {
    assertThrows(
        UnsupportedOperationException.class,
        accountRepositoryMock::findAll);
  }

  @Test
  void findAllById() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.findAllById(null));
  }

  @Test
  void count() {
    assertThrows(
        UnsupportedOperationException.class,
        accountRepositoryMock::count);
  }

  @Test
  void deleteById() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.deleteById(null));
  }

  @Test
  void delete() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.delete(null));
  }

  @Test
  void deleteAllById() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.deleteAllById(null));
  }

  @Test
  void deleteAll() {
    assertThrows(
        UnsupportedOperationException.class,
        accountRepositoryMock::deleteAll);
  }

  @Test
  void testDeleteAll() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> accountRepositoryMock.deleteAll(null));
  }
}
