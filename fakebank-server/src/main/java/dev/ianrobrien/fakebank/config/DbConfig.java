package dev.ianrobrien.fakebank.config;

import javax.sql.DataSource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import dev.ianrobrien.fakebank.repository.AccountRepository;
import dev.ianrobrien.fakebank.repository.AccountRepositoryMock;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/***
 * Configures the database connection
 */
@Configuration
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
@Slf4j
public class DbConfig {

  private String url;
  private String username;
  private String password;
  private String driverClassName;

  /***
   * Configures the database connection
   * @return the data source
   */
  @Bean
  @Profile({"local", "dev"})
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(
        url,
        username,
        password
    );
    dataSource.setDriverClassName(driverClassName);

    return dataSource;
  }

  /***
   * Configures the account provider to use a mock repository
   * @return the account provider
   */
  @Bean
  @Profile({"mock"})
  public AccountRepository accountRepositoryMock() {
    log.info("Using mock account provider");
    return new AccountRepositoryMock();
  }
}
