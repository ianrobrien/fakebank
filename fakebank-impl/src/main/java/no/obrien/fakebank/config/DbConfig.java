package no.obrien.fakebank.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

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
  @Profile({"prod", "local"})
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(
        url,
        username,
        password
    );
    dataSource.setDriverClassName(driverClassName);

    return dataSource;
  }
}
