package dev.ianrobrien.fakebank.accounts;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/***
 * Mock repository class that returns a static set of accounts.
 * These accounts are returned by reference such that their values may be
 * changed during runtime.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public final class AccountRepositoryMock implements AccountRepository {

  // The initial accounts to be returned by the mock repository
  private final List<Account> accounts =
      List.of(
          Account.builder()
              .id(1L)
              .currency("GBP")
              .balance(100)
              .accountOwner(AccountOwner.builder()
                  .id(1L)
                  .firstName("Ian Robert")
                  .lastName("O'Brien")
                  .build())
              .build(),
          Account.builder()
              .id(2L)
              .currency("GBP")
              .balance(200)
              .accountOwner(AccountOwner.builder()
                  .id(1L)
                  .firstName("Jenny Wold")
                  .lastName("O'Brien")
                  .build())
              .build());

  @Override
  public Optional<Account> findById(Long accountId) {
    log.info("Fetching account from repository with id {}", accountId);
    return accounts.stream()
        .filter(a -> a.getId().equals(accountId))
        .findFirst();
  }

  @Override
  public <S extends Account> S save(S entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean existsById(Long aLong) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<Account> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<Account> findAllById(Iterable<Long> longs) {
    throw new UnsupportedOperationException();
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteById(Long aLong) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(Account entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteAll(Iterable<? extends Account> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException();
  }
}
