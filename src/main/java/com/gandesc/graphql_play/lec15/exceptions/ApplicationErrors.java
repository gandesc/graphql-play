package com.gandesc.graphql_play.lec15.exceptions;

import com.gandesc.graphql_play.lec15.dto.CustomerDto;
import org.springframework.graphql.execution.ErrorType;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ApplicationErrors {
  public static <T> Mono<T> nuSuchUser(Integer id) {
    return Mono.error(new ApplicationException(
        ErrorType.BAD_REQUEST,
        "No such user",
        Map.of("customerId", id)
    ));
  }

  public static Mono<CustomerDto> invalidUser(String message, CustomerDto customer) {
    return Mono.error(new ApplicationException(
        ErrorType.BAD_REQUEST,
        message,
        Map.of("input", customer)
    ));
  }
}
