package com.gandesc.graphql_play.lec07.config;

import com.gandesc.graphql_play.lec07.service.CustomerOrderDataFetcher;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Collections;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DataFetcherWiringConfig {
  private final CustomerOrderDataFetcher fetcher;

  @Bean
  public RuntimeWiringConfigurer configurer() {
    return c -> c.type("Query", b -> b.dataFetcher("customers", fetcher))
        .build();
  }

  //example for mapping multiple fields
  private Map<String, DataFetcher> dataFetchersMap() {
    return Map.of(
        "customers", dfe -> Collections.emptyList(),
        "age", dfe -> 12,
        "city", dfe -> "Atlanta"
    );
  }
}

