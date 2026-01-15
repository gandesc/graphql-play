package com.gandesc.graphql_play.lec16.serverapp.repository;

import com.gandesc.graphql_play.lec16.serverapp.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
