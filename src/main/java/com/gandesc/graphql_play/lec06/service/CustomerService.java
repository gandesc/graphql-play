package com.gandesc.graphql_play.lec06.service;

import com.gandesc.graphql_play.lec06.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
@Service
public class CustomerService {
  private final Flux<Customer> flux = Flux.just(
      new Customer(1, "sam", 20, "atlanta"),
      new Customer(2, "jake", 10, "las vegas"),
      new Customer(3, "mike", 15, "miami"),
      new Customer(4, "john", 5, "huston")
  );

  public Flux<Customer> allCustomers() {
    return flux.delayElements(Duration.ofSeconds(1))
        .doOnNext(c -> log.info("customer {}", c.getName()));
  }

}
