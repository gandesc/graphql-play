package com.gandesc.graphql_play.lec06;

import com.gandesc.graphql_play.lec06.dto.Customer;
import com.gandesc.graphql_play.lec06.dto.CustomerOrderDto;
import com.gandesc.graphql_play.lec06.service.CustomerService;
import com.gandesc.graphql_play.lec06.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerRestController {
  private final CustomerService service;
  private final OrderService orderService;

  @GetMapping("customer")
  public Flux<CustomerWithOrder> customers() {
    return this.service.allCustomers()
        .flatMap(c -> orderService.ordersByCustomerName(c.getName())
            .collectList()
            .map(l -> CustomerWithOrder.builder()
                .id(c.getId())
                .age(c.getAge())
                .name(c.getName())
                .city(c.getCity())
                .orders(l)
                .build())
        );
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class CustomerWithOrder {
    private Integer id;
    private String name;
    private Integer age;
    private String city;
    private List<CustomerOrderDto> orders;
  }
}
