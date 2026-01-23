package com.gandesc.graphql_play.lec16.clientapp.client;

import com.gandesc.graphql_play.lec16.dto.CustomerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.WebSocketGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Flux;

@Component
public class SubscriptionClient {
  private final WebSocketGraphQlClient client;

  public SubscriptionClient(@Value("${customer.events.subscription.url}") String baseUrl) {
    this.client = WebSocketGraphQlClient
        .builder(baseUrl, new ReactorNettyWebSocketClient())
        .build();
  }
  /*
  subscription {
    customerEvents {
      id
      action
    }
  }
  */
  public Flux<CustomerEvent> customerEvents() {
    var doc = """
        subscription {
            customerEvents {
              id
              action
            }
          }
        """;

    return this.client.document(doc)
        .retrieveSubscription("customerEvents")
        .toEntity(CustomerEvent.class);
  }
}
