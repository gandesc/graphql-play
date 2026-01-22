package com.gandesc.graphql_play.lec16.clientapp.client;

import com.gandesc.graphql_play.lec16.dto.CustomerDto;
import com.gandesc.graphql_play.lec16.dto.CustomerNotFound;
import com.gandesc.graphql_play.lec16.dto.CustomerResponse;
import com.gandesc.graphql_play.lec16.dto.GenericResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

  public Mono<GenericResponse<CustomerDto>> getCustomerById(Integer id) {
    return this.client.documentName("customer-by-id")
        .variable("id", id)
        .execute()
        .map(cr -> {
          var field = cr.field("customerById");
          return Objects.nonNull(field.getValue())
              ? new GenericResponse<>(field.toEntity(CustomerDto.class))
              : new GenericResponse<>(field.getErrors());
        });
  }

  public Mono<CustomerResponse> getCustomerByIdWithUnion(Integer id) {
    return this.client.documentName("customer-by-id")
        .variable("id", id)
        .execute()
        .map(cr -> {
          var field = cr.field("customerById");

          var isCustomer = "Customer".equals(cr.field("customerById.type")
              .getValue().toString());

          return isCustomer
              ? field.toEntity(CustomerDto.class)
              : field.toEntity(CustomerNotFound.class);
        });
  }

  public Mono<List<CustomerDto>> allCustomers() {
    return this.crud("GetAll", Collections.emptyMap(),
        new ParameterizedTypeReference<>() {
        });
  }

  public Mono<CustomerDto> customerById(Integer id) {
    return this.crud("GetCustomerById", Map.of("id", id),
        new ParameterizedTypeReference<>() {
        });
  }

  public Mono<CustomerDto> createCustomer(CustomerDto cust) {
    return this.crud("CreateCustomer", Map.of("customer", cust),
        new ParameterizedTypeReference<>() {
        });
  }

  public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto cust) {
    return this.crud("UpdateCustomer", Map.of("id", id, "customer", cust),
        new ParameterizedTypeReference<>() {
        });
  }

  private <T> Mono<T> crud(String opName, Map<String, Object> vars, ParameterizedTypeReference<T> returnType) {
    return this.client.documentName("crud-operations")
        .operationName(opName)
        .variables(vars)
        .retrieve("response")
        .toEntity(returnType);
  }
}
