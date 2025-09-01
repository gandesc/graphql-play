package com.gandesc.graphql_play.lec11;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Controller
public class ResultsController {
  private static final List<Object> list = List.of(
      FruitDto.builder()
          .description("banana")
          .expiration(LocalDate.now().plusDays(3))
          .build(),
      FruitDto.builder()
          .description("apple")
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
          .title("Java")
          .author("Meyers")
          .build()
  );

  @QueryMapping
  public Flux<Object> search() {
    return Mono.fromSupplier(() -> new ArrayList<>(list))
        .doOnNext(Collections::shuffle)
        .flatMapIterable(Function.identity())
        .take(ThreadLocalRandom.current().nextInt(0, list.size()))
        ;
  }
}
