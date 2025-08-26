package com.gandesc.graphql_play.lec06;

import com.gandesc.graphql_play.lec06.dto.CustomerWithOrder;
import com.gandesc.graphql_play.lec06.service.CustomerService;
import com.gandesc.graphql_play.lec06.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerRestController {
  private final CustomerService service;
  private final OrderService orderService;

  @GetMapping("customers")
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

    //    return this.service.allCustomers()
    //        .publishOn(Schedulers.boundedElastic())
    //        .map(c ->  {
    //          var orders = new ArrayList<CustomerOrderDto>();
    //          orderService.ordersByCustomerName(c.getName())
    //              .collectList().subscribe(orders::addAll);
    //
    //          var cust = CustomerWithOrder.builder()
    //              .id(c.getId())
    //              .age(c.getAge())
    //              .name(c.getName())
    //              .city(c.getCity())
    //              .orders(orders)
    //              .build();
    //
    //        return cust;
    //        });
  }
}
