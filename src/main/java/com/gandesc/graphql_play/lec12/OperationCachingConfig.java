package com.gandesc.graphql_play.lec12;

import graphql.ExecutionInput;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Slf4j
@Configuration
public class OperationCachingConfig {
  private Map<String, PreparsedDocumentEntry> cache = new ConcurrentHashMap<>();

  @Bean
  public GraphQlSourceBuilderCustomizer customizer(PreparsedDocumentProvider provider) {

    return c -> c
        .configureGraphQl(builder -> builder.preparsedDocumentProvider(provider));
  }

  @Bean
  public PreparsedDocumentProvider provider() {

    return new PreparsedDocumentProvider() {

      @Override
      public CompletableFuture<PreparsedDocumentEntry> getDocumentAsync(
          ExecutionInput executionInput,
          Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {

        var entry = cache.computeIfAbsent(executionInput.getQuery(), key -> {
          log.info("Not found key {}", key);
          var r = parseAndValidateFunction.apply(executionInput);
          log.info("Caching: {}", r);

          return r;
        });

        return CompletableFuture.completedFuture(entry);
      }
    };
  }
}