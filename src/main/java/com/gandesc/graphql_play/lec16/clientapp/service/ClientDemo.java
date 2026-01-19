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
    getCustomerById().subscribe();
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

    return Mono.delay(Duration.ofSeconds(1))
        .doFirst(() -> log.info("Raw Query"))
        .then(mono)
        .doOnNext(i -> log.info("result: {}", i))
        .then();
  }

  private Mono<Void> getCustomerById() {
    return Mono.delay(Duration.ofSeconds(1))
        .doFirst(() -> log.info("Get Customer By Id"))
        .then(this.client.getCustomerById(1))
        .doOnNext(i -> log.info("result: {}", i))
        .then();
  }
}
