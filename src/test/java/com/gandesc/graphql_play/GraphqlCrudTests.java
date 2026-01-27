package com.gandesc.graphql_play;

import com.gandesc.graphql_play.lec16.dto.CustomerDto;
import com.gandesc.graphql_play.lec16.dto.DeleteResponseDto;
import com.gandesc.graphql_play.lec16.dto.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestPropertySource(properties = "lec=lec14")
class GraphqlCrudTests {
  @Autowired
  private HttpGraphQlTester client;

  @Test
  void allCustomersTest() {
    var doc = """
        query {
          customers {
            name
            age
          }
        }
        """;

    this.client.document(doc)
        .execute()
        .path("customers").entityList(Object.class).hasSizeGreaterThan(2)
        .path("customers.[0].name").entity(String.class).isEqualTo("sam");

  }

  @Test
  void customerByIdTest() {
    this.client.documentName("crud-operations")
        .variable("id", 1)
        .operationName("GetCustomerById")
        .execute()
        .path("response.id").entity(Integer.class).isEqualTo(1)
        .path("response.name").entity(String.class).isEqualTo("sam")
        .path("response.age").entity(Integer.class).isEqualTo(10);
  }

  @Test
  void createCustomerTest() {
    var dto = CustomerDto.builder()
        .name("michael")
        .age(55)
        .city("seattle")
        .build();

    this.client.documentName("crud-operations")
        .variable("customer", dto)
        .operationName("CreateCustomer")
        .execute()
        .path("response.id").entity(Integer.class).isEqualTo(5)
        .path("response.name").entity(String.class).isEqualTo("michael")
        .path("response.age").entity(Integer.class).isEqualTo(55);
  }

  @Test
  void updateCustomerTest() {
    var dto = CustomerDto.builder()
        .name("obie")
        .age(45)
        .city("dallas")
        .build();

    this.client.documentName("crud-operations")
        .variable("id", 2)
        .variable("customer", dto)
        .operationName("UpdateCustomer")
        .execute()
        .path("response.id").entity(Integer.class).isEqualTo(2)
        .path("response.name").entity(String.class).isEqualTo("obie")
        .path("response.age").entity(Integer.class).isEqualTo(45)
        .path("response").entity(CustomerDto.class).satisfies(c -> {});
  }

  @Test
  void deleteCustomerTest() {
    this.client.documentName("crud-operations")
        .variable("id", 3)
        .operationName("DeleteCustomer")
        .execute()
        .path("response").entity(DeleteResponseDto.class).satisfies(c -> {
          assertThat(c.getId()).isEqualTo(3);
          assertThat(c.getStatus()).isEqualTo(Status.SUCCESS);
        });;
  }
}
