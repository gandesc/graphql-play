package com.gandesc.graphql_play.lec14.controller;

import com.gandesc.graphql_play.lec14.dto.CustomerEvent;
import com.gandesc.graphql_play.lec14.service.CustomerEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerEventController {
  private final CustomerEventService service;

  @SubscriptionMapping
  public Flux<CustomerEvent> customerEvents() {
    return this.service.subscribe();
  }
}
