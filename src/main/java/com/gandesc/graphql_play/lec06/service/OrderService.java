package com.gandesc.graphql_play.lec06.service;

import com.gandesc.graphql_play.lec06.dto.CustomerOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
public class OrderService {
  private final Map<String, List<CustomerOrderDto>> map = Map.of(
      "sam", List.of(
          new CustomerOrderDto(UUID.randomUUID(), "sam-product-1"),
          new CustomerOrderDto(UUID.randomUUID(), "sam-product-2")
      )
//      "mike", List.of(
//          new CustomerOrderDto(UUID.randomUUID(), "mike-product-1"),
//          new CustomerOrderDto(UUID.randomUUID(), "mike-product-2"),
//          new CustomerOrderDto(UUID.randomUUID(), "mike-product-3")
//      )
  );

  public Flux<CustomerOrderDto> ordersByCustomerName(String name) {
    return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()))
        .delayElements(Duration.ofSeconds(1))
        .doOnNext(o -> log.info("orders for {}", name));
  }
}
