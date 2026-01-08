package com.gandesc.graphql_play.lec15.repository;

import com.gandesc.graphql_play.lec15.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
