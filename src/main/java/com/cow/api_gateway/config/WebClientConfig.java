package com.cow.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${spring.cloud.gateway.routes[1].uri}")
  private String url;

  @Bean
  public WebClient ssoWebClient() {
    return WebClient.create(url);
  }
  

}
