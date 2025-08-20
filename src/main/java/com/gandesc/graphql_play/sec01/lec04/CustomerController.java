package com.gandesc.graphql_play.sec01.lec04;

import com.gandesc.graphql_play.sec01.lec04.dto.Customer;
import com.gandesc.graphql_play.sec01.lec04.dto.CustomerOrderDto;
import com.gandesc.graphql_play.sec01.lec04.service.CustomerService;
import com.gandesc.graphql_play.sec01.lec04.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

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

//  @BatchMapping(typeName = "Customer")
//  public Flux<List<CustomerOrderDto>> orders(List<Customer> list) {
//    log.info("fetching list of orders for customers {}", list);
//
//    List<String> names = list.stream()
//        .map(Customer::getName)
//        .toList();
//
//    return this.orderService.ordersByCustomerNames(names);
//  }

  @BatchMapping(typeName = "Customer")
  public Mono<Map<Customer, List<CustomerOrderDto>>> orders(List<Customer> list) {
    log.info("fetching list of orders for customers {}", list);

    return this.orderService.fetchOrdersAsMap(list);
  }

  @SchemaMapping(typeName = "Customer")
  public Mono<Integer> age() {
    return Mono.just(100);
  }

}
