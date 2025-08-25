package com.gandesc.graphql_play.lec06;

import com.gandesc.graphql_play.lec06.dto.Customer;
import com.gandesc.graphql_play.lec06.dto.CustomerOrderDto;
import com.gandesc.graphql_play.lec06.service.CustomerService;
import com.gandesc.graphql_play.lec06.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
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
  public Flux<CustomerOrderDto> orders(Customer customer) {
    return this.orderService.ordersByCustomerName(customer.getName());
  }

}
