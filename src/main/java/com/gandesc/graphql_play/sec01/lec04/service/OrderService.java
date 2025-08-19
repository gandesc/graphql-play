package com.gandesc.graphql_play.sec01.lec04.service;

import com.gandesc.graphql_play.sec01.lec04.dto.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;



@Service
public class OrderService {
  private final Map<String, List<CustomerOrderDto>> map = Map.of(
      "sam", List.of(
          new CustomerOrderDto(UUID.randomUUID(), "sam-product-1"),
          new CustomerOrderDto(UUID.randomUUID(), "sam-product-2")
      ),
      "mike", List.of(
          new CustomerOrderDto(UUID.randomUUID(), "mike-product-1"),
          new CustomerOrderDto(UUID.randomUUID(), "mike-product-2"),
          new CustomerOrderDto(UUID.randomUUID(), "mike-product-3")
      )
  );

  public Flux<CustomerOrderDto> ordersByCustomerNames(String name) {
    return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
  }

  public Flux<List<CustomerOrderDto>> ordersByCustomerNames(List<String> names) {
    return Flux.fromIterable(names)
        .flatMap(n-> fetchOrders(n)
            //comment below for size mismatch demo
            .defaultIfEmpty(Collections.emptyList())
        );
  }

  private Mono<List<CustomerOrderDto>> fetchOrders(String name) {
    return Mono.justOrEmpty(map.get(name));
  }
}
