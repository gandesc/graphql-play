package com.gandesc.graphql_play.lec10;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Controller
public class ProductController {
  @QueryMapping
  public Flux<Object> products() {
    return Flux.just(
        Fruit.builder()
            .description("banana")
            .price(1)
            .expiration(LocalDate.now().plusDays(3))
            .build(),
        Fruit.builder()
            .description("apple")
            .price(1)
            .expiration(LocalDate.now().plusDays(5))
            .build(),
        Electronics.builder()
            .description("iphone")
            .price(900)
            .brand("APPLE")
            .build(),
        Electronics.builder()
            .description("Galaxy")
            .price(800)
            .brand("SAMSUNG")
            .build(),
        Book.builder()
            .description("Java")
            .price(40)
            .author("Meyers")
            .build()
        );
  }
}
