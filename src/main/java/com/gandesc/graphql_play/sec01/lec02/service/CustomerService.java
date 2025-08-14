package com.gandesc.graphql_play.sec01.lec02.service;

import com.gandesc.graphql_play.sec01.lec02.dto.AgeRangeFilter;
import com.gandesc.graphql_play.sec01.lec02.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
  private final Flux<Customer> flux = Flux.just(
      new Customer(1, "sam", 20, "atlanta"),
      new Customer(2, "jake", 10, "las vegas"),
      new Customer(3, "mike", 15, "miami"),
      new Customer(4, "john", 5, "huston")
  );

  public Flux<Customer> allCustomers() {
    return  flux;
  }

  public Mono<Customer> customerById(Integer id) {
    return flux
        .filter(c -> c.getId().equals(id))
        .next();
  }

  public Flux<Customer> nameContains(String name) {
    return flux
        .filter(c -> c.getName().contains(name));
  }

  public Flux<Customer> customersByAgeRange(AgeRangeFilter filter) {
    return flux
        .filter(c -> c.getAge() >= filter.getMinAge() && c.getAge() <= filter.getMaxAge());
  }
}
