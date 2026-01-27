package com.gandesc.graphql_play.lec15.controller;

import com.gandesc.graphql_play.lec15.exceptions.ApplicationException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class ExceptionResolver implements DataFetcherExceptionResolver {
  @Override
  public Mono<List<GraphQLError>> resolveException(Throwable t, DataFetchingEnvironment env) {
    var ex = toApplicationException(t);

    GraphQLError err = GraphqlErrorBuilder.newError(env)
        .message(t.getMessage())
        .errorType(ex.getErrorType())
        .extensions(ex.getExtensions())
        .build();

    return Mono.just(List.of(err));
  }

  private ApplicationException toApplicationException(Throwable t) {
    return ApplicationException.class.equals(t.getClass())
        ? (ApplicationException) t
        : new ApplicationException(ErrorType.INTERNAL_ERROR, t.getMessage(), Collections.emptyMap());
  }
}
