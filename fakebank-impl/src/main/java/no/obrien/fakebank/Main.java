package no.obrien.fakebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/***
 * Main entry point for the application
 */
@SpringBootApplication
@EnableZuulProxy
public class Main {

  /***
   * Main entry point for the application
   * @param args the command line arguments for the application
   */
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
