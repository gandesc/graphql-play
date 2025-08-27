package com.gandesc.graphql_play.lec07.service;

import com.gandesc.graphql_play.lec07.dto.CustomerWithOrder;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.UnaryOperator;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerOrderDataFetcher implements DataFetcher<Flux<CustomerWithOrder>> {
  private final CustomerService customerService;
  private final OrderService orderService;

  @Override
  public Flux<CustomerWithOrder> get(DataFetchingEnvironment environment) {
    var includeOrders = environment.getSelectionSet().contains("orders");

    log.info("did ask for orders? {}", includeOrders);

    return this.customerService.allCustomers()
        .map(c -> CustomerWithOrder.builder()
            .id(c.getId())
            .age(c.getAge())
            .name(c.getName())
            .city(c.getCity())
            .orders(Collections.emptyList())
            .build())
        .transform(this.updateOrdersTransformer(includeOrders));
  }

  private UnaryOperator<Flux<CustomerWithOrder>> updateOrdersTransformer(boolean includeOrders) {
    return includeOrders
        ? f -> f.flatMapSequential(this::fetchOrders)
        : f -> f;
  }

  private Mono<CustomerWithOrder> fetchOrders(CustomerWithOrder customerWithOrder) {
    return this.orderService.ordersByCustomerName(customerWithOrder.getName())
        .collectList()
        .doOnNext(customerWithOrder::setOrders)
        .thenReturn(customerWithOrder);
  }
}
