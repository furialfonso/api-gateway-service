package com.cow.apigateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

  private static final String AUTHORIZATION = "Authorization";
  private static final String URI = "/auth/valid-token";

  @Value("${spring.cloud.gateway.routes[1].uri}")
  private String path;

  @Autowired private WebClient.Builder webClientBuilder;

  @Override
  public Mono<Boolean> isValidToken(String token) {
    return this.webClientBuilder
        .build()
        .post()
        .uri(path + URI)
        .header(AUTHORIZATION, token)
        .exchangeToMono(
            response -> {
              return Mono.just(response.statusCode() == HttpStatus.OK);
            });
  }
}
