package com.gandesc.graphql_play.lec15.controller;

import com.gandesc.graphql_play.lec15.dto.CustomerDto;
import com.gandesc.graphql_play.lec15.dto.CustomerNotFound;
import com.gandesc.graphql_play.lec15.dto.DeleteResponseDto;
import com.gandesc.graphql_play.lec15.exceptions.ApplicationErrors;
import com.gandesc.graphql_play.lec15.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService service;

  @QueryMapping
  public Flux<CustomerDto> customers() {
    return this.service.allCustomers();
  }

  @QueryMapping
  public Mono<Object> customerById(@Argument Integer id) {
    return this.service.customerById(id)
        .cast(Object.class)
        .switchIfEmpty(Mono.just(CustomerNotFound.builder().id(id).build()));
  }

  @MutationMapping
  public Mono<CustomerDto> createCustomer(@Argument CustomerDto customer) {
    if(customer.getAge() !=null && customer.getAge() < 18) {
      return ApplicationErrors.invalidUser("user is minor", customer);
    }

    return this.service.createCustomer(customer);
  }

  @MutationMapping
  public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument("customer") CustomerDto dto) {
    return this.service.updateCustomer(id, dto);
  }

  @MutationMapping
  public Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id) {
    return this.service.deleteCustomer(id);
  }
}
