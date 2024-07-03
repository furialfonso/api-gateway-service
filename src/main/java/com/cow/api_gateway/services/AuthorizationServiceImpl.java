package com.cow.api_gateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cow.api_gateway.config.WebClientConfig;

import reactor.core.publisher.Mono;

@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

  private static final String AUTHORIZATION = "Authorization";
  private static final String URI = "/auth/valid-token";

  @Autowired
  private WebClientConfig webClient;

  @Override
  public Mono<Boolean> isValidToken(String token) {
    return webClient.ssoWebClient().post().uri(URI).header(AUTHORIZATION, token)
        .exchangeToMono(response -> {
          return Mono.just(response.statusCode() == HttpStatus.OK);
        });
  }
}
