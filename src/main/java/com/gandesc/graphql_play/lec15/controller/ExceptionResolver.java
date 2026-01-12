package com.gandesc.graphql_play.lec15.controller;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class ExceptionResolver implements DataFetcherExceptionResolver {
  @Override
  public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
    GraphQLError err = GraphqlErrorBuilder.newError(environment)
        .message(exception.getMessage())
        .errorType(ErrorType.INTERNAL_ERROR)
        .extensions(Map.of(
            "customerId", 123,
            "timestamp", LocalDateTime.now(),
            "foo", "bar"
        ))
        .build();

    return Mono.just(List.of(err));
  }
}
