package no.obrien.fakebank.config;

import lombok.extern.java.Log;
import no.obrien.fakebank.provider.AccountProvider;
import no.obrien.fakebank.repository.AccountRepository;
import no.obrien.fakebank.repository.AccountRepositoryMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/***
 * Configures the account provider
 */
@Configuration
@Log
public class AccountProviderConfig {

  /***
   * Configures the account provider to use a mock repository
   * @return the account provider
   */
  @Bean
  @Profile({"default", "mock"})
  public AccountProvider accountProviderMock() {
    log.info("Using mock account provider");
    return new AccountProvider(new AccountRepositoryMock());
  }

  /***
   * Configures the account provider to use the real database
   * @return the account provider
   */
  @Bean
  @Profile({"prod", "local"})
  public AccountProvider accountProvider(AccountRepository accountRepository) {
    log.info("Using real account provider");
    return new AccountProvider(accountRepository);
  }
}
