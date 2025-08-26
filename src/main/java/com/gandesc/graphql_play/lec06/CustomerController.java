package com.gandesc.graphql_play.lec06;

import com.gandesc.graphql_play.lec06.dto.Customer;
import com.gandesc.graphql_play.lec06.dto.CustomerOrderDto;
import com.gandesc.graphql_play.lec06.dto.CustomerWithOrder;
import com.gandesc.graphql_play.lec06.service.CustomerOrderDataFetcher;
import com.gandesc.graphql_play.lec06.service.CustomerService;
import com.gandesc.graphql_play.lec06.service.OrderService;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerOrderDataFetcher dataFetcher;

  @SchemaMapping(typeName = "Query")
  public Flux<CustomerWithOrder> customers(DataFetchingFieldSelectionSet selectionSet) {
    return this.dataFetcher.customers(selectionSet);
  }

//  @SchemaMapping(typeName = "Customer")
//  public Flux<CustomerWithOrder> orders(Customer customer) {
//    return this.dataFetcher.customers(customer.getName());
//  }

}
