package dev.ianrobrien.fakebank.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import dev.ianrobrien.fakebank.exception.InvalidAccountException;
import dev.ianrobrien.fakebank.mapper.AccountMapper;
import dev.ianrobrien.fakebank.model.Account;
import dev.ianrobrien.fakebank.model.AccountBalance;
import dev.ianrobrien.fakebank.model.AccountDetails;
import dev.ianrobrien.fakebank.repository.AccountRepository;
import org.springframework.stereotype.Service;

/***
 * An implementation of AccountService which fetches accounts from a repository
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  /***
   * Gets an account from the given account id
   * @param accountId the account id of the requested account
   * @return the requested account
   * @throws InvalidAccountException when the account id is invalid
   */
  public Account getAccount(Long accountId) throws InvalidAccountException {
    log.info("Getting account from account provider with id {}", accountId);
    return Optional.of(accountRepository.findById(accountId))
        .get()
        .orElseThrow(InvalidAccountException::new);
  }

  /***
   * Gets an account details from the given account id
   * @param accountId the account id of the requested account
   * @return the requested account details
   * @throws InvalidAccountException when the account id is invalid
   */
  public AccountDetails getAccountDetails(Long accountId) throws InvalidAccountException {
    return accountMapper.toAccountDetails(this.getAccount(accountId));
  }

  /***
   * Gets an account balance from the given account id
   * @param accountId the account id of the requested account
   * @return the requested account balance
   * @throws InvalidAccountException when the account id is invalid
   */
  public AccountBalance getAccountBalance(Long accountId) throws InvalidAccountException {
    return accountMapper.toAccountBalance(this.getAccount(accountId));
  }

  /***
   * Saves an account entity
   * @param account the account to save
   */
  public void saveAccount(Account account) {
    accountRepository.save(account);
  }
}
