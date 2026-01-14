package com.gandesc.graphql_play.lec15.controller;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestInterceptor implements WebGraphQlInterceptor {
  // client has to pass header caller-id
  @Override
  public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest req, Chain chain) {
    var headers = req.getHeaders().getOrEmpty("caller-id");
    var callerId = headers.isEmpty()
        ? ""
        : headers.getFirst();

    req
        .configureExecutionInput((e, b) -> b.graphQLContext(createMap(callerId)).build());

    return chain.next(req);
  }

  private static Map<String, Object> createMap(String callerId) {
    Map<String, Object> map = new HashMap<>();

    map.put("caller-id", callerId);
    
    return map;
  }
}
