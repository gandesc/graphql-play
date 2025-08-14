package com.gandesc.graphql_play.sec01.lec03;

import com.gandesc.graphql_play.sec01.lec03.dto.Customer;
import com.gandesc.graphql_play.sec01.lec03.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService service;

  @QueryMapping
  public Flux<Customer> customers() {
    return this.service.allCustomers();
  }

}
