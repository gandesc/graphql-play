package com.gandesc.graphql_play.lec05;

import com.gandesc.graphql_play.lec05.dto.Account;
import com.gandesc.graphql_play.lec05.dto.Customer;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Controller
public class AccountController {

  @SchemaMapping(typeName = "Customer")
  public Mono<Account> account(Customer customer, DataFetchingFieldSelectionSet selectionSet) {
    log.info("account: {}", selectionSet.getFields());

    var type = ThreadLocalRandom.current().nextBoolean()
        ? "CHECKING"
        : "SAVING";

    return Mono.just(Account.builder()
        .id(UUID.randomUUID())
        .accountType(type)
        .amount(ThreadLocalRandom.current().nextInt(1, 1000))
        .build());
  }
}
