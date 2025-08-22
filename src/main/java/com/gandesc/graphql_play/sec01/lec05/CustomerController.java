package com.gandesc.graphql_play.sec01.lec05;

import com.gandesc.graphql_play.sec01.lec05.dto.Customer;
import com.gandesc.graphql_play.sec01.lec05.service.CustomerService;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService service;

  @QueryMapping
  public Flux<Customer> customers(DataFetchingFieldSelectionSet selectionSet) {
    log.info("customer: {}", selectionSet.getFields());

    return this.service.allCustomers();
  }

}
