package com.gandesc.graphql_play.sec01.lec05;

import com.gandesc.graphql_play.sec01.lec05.dto.Address;
import com.gandesc.graphql_play.sec01.lec05.dto.Customer;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

  @SchemaMapping(typeName = "Customer")
  public Mono<Address> address(Customer customer) {
    return Mono.just(Address.builder()
        .street(customer.getName() + " street")
        .city(customer.getName() + " city")
        .build());
  }
}
