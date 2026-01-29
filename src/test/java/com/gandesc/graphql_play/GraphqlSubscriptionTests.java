package com.gandesc.graphql_play;

import com.gandesc.graphql_play.lec14.dto.Action;
import com.gandesc.graphql_play.lec14.dto.CustomerEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.graphql.test.tester.WebSocketGraphQlTester;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureHttpGraphQlTester
@TestPropertySource(properties = "lec=lec14")
class GraphqlSubscriptionTests {
  private static final String WS_PATH = "ws://localhost:8080/graphql";
  @Autowired
  private HttpGraphQlTester client;


  @Test
  void subscriptionTest() {
    var websocketClient = WebSocketGraphQlTester
        .builder(WS_PATH, new ReactorNettyWebSocketClient())
        .build();

    this.client
        .documentName("crud-operations")
        .operationName("DeleteCustomer")
        .variable("id", 1)
        .executeAndVerify(); //ensure there are no errors in the response

    websocketClient.documentName("subscription-test")
        .executeSubscription()
        .toFlux("customerEvents", CustomerEvent.class)
        .take(1)
        .as(StepVerifier::create)
        .consumeNextWith(e -> {
          assertThat(e.getAction())
              .isEqualTo(Action.DELETED);
        });
  }
}
