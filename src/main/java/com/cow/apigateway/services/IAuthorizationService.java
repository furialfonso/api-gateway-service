package com.cow.apigateway.services;

import reactor.core.publisher.Mono;

public interface IAuthorizationService {

  public Mono<Boolean> isValidToken(String token);
}
