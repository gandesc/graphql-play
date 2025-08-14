package com.gandesc.graphql_play.sec01.lec03.service;

import com.gandesc.graphql_play.sec01.lec03.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
  private final Flux<Customer> flux = Flux.just(
      new Customer(1, "sam", 20, "atlanta"),
      new Customer(2, "jake", 10, "las vegas"),
      new Customer(3, "mike", 15, "miami"),
      new Customer(4, "john", 5, "huston")
  );

  public Flux<Customer> allCustomers() {
    return flux;
  }

}
