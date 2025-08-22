package com.gandesc.graphql_play.sec01.lec05;

import com.gandesc.graphql_play.sec01.lec05.dto.Address;
import com.gandesc.graphql_play.sec01.lec05.dto.Customer;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AddressController {

  @SchemaMapping(typeName = "Customer")
  public Mono<Address> address(Customer customer, DataFetchingEnvironment environment) {
    log.info("address: {}", environment.getDocument());

    return Mono.just(Address.builder()
        .street(customer.getName() + " street")
        .city(customer.getName() + " city")
        .build());
  }
}
