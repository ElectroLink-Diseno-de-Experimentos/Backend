package com.hampcoders.electrolink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The main application class for the Electrolink Platform.
 */
@SpringBootApplication
@EnableJpaAuditing
public class ElectrolinkPlatformApplication {

  /**
   * The main method to run the Spring Boot application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(ElectrolinkPlatformApplication.class, args);
  }
}