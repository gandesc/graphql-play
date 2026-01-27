package com.gandesc.graphql_play;

import com.gandesc.graphql_play.lec16.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestPropertySource(properties = "lec=lec15")
class GraphqlErrorTests {
  @Autowired
  private HttpGraphQlTester client;


  @Test
  void createCustomerTest() {
    var dto = CustomerDto.builder()
        .name("michael")
        .age(15)
        .city("seattle")
        .build();

    this.client
        .mutate().header("caller-id", "1").build()
        .documentName("crud-operations")
        .variable("customer", dto)
        .operationName("CreateCustomer")
        .execute()
        .errors().satisfy(list -> {
          assertThat(list).hasSize(1);
          assertThat(list.getFirst().getErrorType())
              .isEqualTo(ErrorType.BAD_REQUEST);
        })
        .path("response").valueIsNull();
  }

  /*
  {
    "errors": [],
    "response": null,
  }
   */
}
