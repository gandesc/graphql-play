package com.gandesc.graphql_play.lec16.clientapp.client;

import com.gandesc.graphql_play.lec16.dto.CustomerDto;
import com.gandesc.graphql_play.lec16.dto.MultiCustomerAssignment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CustomerClient {
  private final HttpGraphQlClient client;

  public CustomerClient(@Value("${customer.service.url}") String baseUrl) {
    this.client = HttpGraphQlClient.builder()
        .webClient(b -> b.baseUrl(baseUrl))
        .build();
  }

  public Mono<ClientGraphQlResponse> rawQuery(String query) {
    return this.client.document(query).execute();
  }

  public Mono<MultiCustomerAssignment> getCustomerById(Integer id) {
    return this.client.documentName("customer-by-id")
        .variable("id", id)
        .execute()
        .map(cr -> cr.toEntity(MultiCustomerAssignment.class));
  }
}
