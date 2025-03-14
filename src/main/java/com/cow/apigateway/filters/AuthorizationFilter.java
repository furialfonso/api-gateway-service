package com.cow.apigateway.filters;

import com.cow.apigateway.filters.dto.Config;
import com.cow.apigateway.services.IAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<Config> {

  public AuthorizationFilter() {
    super(Config.class);
  }

  private static final String AUTHORIZATION = "Authorization";

  @Autowired private IAuthorizationService authorizationService;

  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      config.setToken(exchange.getRequest().getHeaders().getFirst(AUTHORIZATION));
      if (config.getToken() == null || config.getToken().isEmpty()) {
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        return Mono.empty();
      }
      Mono<Boolean> isValid = authorizationService.isValidToken(config.getToken());
      return isValid.flatMap(
          isValidToken -> {
            if (!isValidToken) {
              exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
              return Mono.empty();
            }
            return chain.filter(exchange);
          });
    };
  }
}
