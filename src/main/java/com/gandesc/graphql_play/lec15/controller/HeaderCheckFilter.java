package com.gandesc.graphql_play.lec15.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class HeaderCheckFilter implements WebFilter {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    boolean isEmpty = exchange.getRequest().getHeaders().getOrEmpty("caller-id")
        .isEmpty();

    return !isEmpty
        ? chain.filter(exchange)
        : Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(BAD_REQUEST));
  }
}
