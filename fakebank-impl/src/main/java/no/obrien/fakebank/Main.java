package no.obrien.fakebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

  /***
   * Main entry point for the application
   * @param args the command line arguments for the application
   */
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
