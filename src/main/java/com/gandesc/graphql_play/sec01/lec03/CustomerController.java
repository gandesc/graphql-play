package com.gandesc.graphql_play.sec01.lec03;

import com.gandesc.graphql_play.sec01.lec03.dto.Customer;
import com.gandesc.graphql_play.sec01.lec03.dto.CustomerOrderDto;
import com.gandesc.graphql_play.sec01.lec03.service.CustomerService;
import com.gandesc.graphql_play.sec01.lec03.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService service;
  private final OrderService orderService;

  @SchemaMapping(typeName = "Query")
  public Flux<Customer> customers() {
    return this.service.allCustomers();
  }

  @SchemaMapping(typeName = "Customer")
  public Flux<CustomerOrderDto> orders(Customer customer, @Argument Integer limit) {
    return this.orderService
        .ordersByCustomerName(customer.getName())
        .take(limit);
  }

}
