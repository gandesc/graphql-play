package com.gandesc.graphql_play.lec16.clientapp.service;

import com.gandesc.graphql_play.lec16.clientapp.client.CustomerClient;
import com.gandesc.graphql_play.lec16.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientDemo implements CommandLineRunner {
  private final CustomerClient client;

  @Override
  public void run(String... args) {
    allCustomersDemo()
        .then(customerByIdDemo())
        .then(createCustomerDemo())
        .subscribe();
  }

  /* {
        "data" : {
          "customers" : []
        }
      }
  * */
  private Mono<Void> rawQueryDemo() {
    String query = """
        {
          customers {
            id
            name
            age
            city
          }
        }
        """;

    Mono<List<CustomerDto>> mono = this.client.rawQuery(query)
        .map(cr -> cr.field("customers").toEntityList(CustomerDto.class));

    return this.executor(
        "Raw Query",
        mono
    );
  }

  private Mono<Void> getCustomerById() {
    return this.executor(
        "Get Customer By Id",
        this.client.getCustomerByIdWithUnion(10)
    );
  }

  private Mono<Void> allCustomersDemo() {
    return this.executor(
        "Get All customers",
        this.client.allCustomers()
    );
  }

  private Mono<Void> customerByIdDemo() {
    return this.executor(
        "Get customer by id",
        this.client.customerById(1)
    );
  }

  private Mono<Void> createCustomerDemo() {
    var cust = CustomerDto.builder().age(15).city("Detroit").name("Fooman").build();

    return this.executor(
        "Create customer",
        this.client.createCustomer(cust)
    );
  }

  private <T> Mono<Void> executor(String message, Mono<T> mono) {
    return Mono.delay(Duration.ofSeconds(1))
        .doFirst(() -> log.info(message))
        .then(mono)
        .doOnNext(i -> log.info("result: {}", i))
        .then();
  }
}
