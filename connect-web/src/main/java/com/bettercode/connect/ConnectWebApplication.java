package com.bettercode.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ConnectWebApplication {

  private Environment env;

  @Autowired
  public void setEnv(Environment env) {
    this.env = env;
  }

  public static void main(String[] args) {
    SpringApplication.run(ConnectWebApplication.class, args);
  }

  @Bean
  public String[] getAllowOrigins() {
    return new String[] {
        env.getProperty("allow_cors_address")
    };
  }

  @Bean
  public String[] getAllowMethods() {
    return new String[] {"GET", "POST", "PUT", "DELETE"};
  }

}
