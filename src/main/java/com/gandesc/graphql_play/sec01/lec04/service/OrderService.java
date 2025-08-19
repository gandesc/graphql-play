package com.gandesc.graphql_play.sec01.lec04.service;

import com.gandesc.graphql_play.sec01.lec04.dto.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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

  public Flux<CustomerOrderDto> ordersByCustomerName(String name) {
    return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
  }

  public Flux<List<CustomerOrderDto>> ordersByCustomerName(List<String> names) {
    return Flux.fromIterable(names)
        .map(name -> map.getOrDefault(name, Collections.emptyList()));
  }
}
