package no.obrien.fakebank.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.User;
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
              .id(111L)
              .currency("GBP")
              .balance(100)
              .owner(User.builder()
                  .id(1L)
                  .firstName("Ian Robert")
                  .lastName("O'Brien")
                  .build())
              .build(),
          Account.builder()
              .id(222L)
              .currency("GBP")
              .balance(200)
              .owner(User.builder()
                  .id(1L)
                  .firstName("Jenny Wold")
                  .lastName("O'Brien")
                  .build())
              .build());

  @Override
  public <S extends Account> S save(S entity) {
    return null;
  }

  @Override
  public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<Account> findById(Long accountId) {
    log.info("Fetching account from repository with id {}", accountId);
    return accounts.stream()
        .filter(a -> a.getId().equals(accountId))
        .findFirst();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public Iterable<Account> findAll() {
    return null;
  }

  @Override
  public Iterable<Account> findAllById(Iterable<Long> longs) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public void delete(Account entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  @Override
  public void deleteAll(Iterable<? extends Account> entities) {

  }

  @Override
  public void deleteAll() {

  }
}
