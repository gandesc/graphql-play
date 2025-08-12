package com.gandesc.graphql_play;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GraphqlController {

  @QueryMapping("sayHello")
  public Mono<String> helloWorld() {
    return Mono.just("Hello world");
  }

  @QueryMapping
  public Mono<String> sayHelloTo(@Argument String name) {
    return Mono.fromSupplier(() -> "Hello " + name);
  }
}
