package com.gandesc.graphql_play.lec14.service;

import com.gandesc.graphql_play.lec14.dto.CustomerDto;
import com.gandesc.graphql_play.lec14.dto.DeleteResponseDto;
import com.gandesc.graphql_play.lec14.repository.CustomerRepository;
import com.gandesc.graphql_play.lec14.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.gandesc.graphql_play.lec14.dto.Status.FAIL;
import static com.gandesc.graphql_play.lec14.dto.Status.SUCCESS;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository repository;

  public Flux<CustomerDto> allCustomers() {
    return repository.findAll()
        .map(EntityDtoUtil::toDto);
  }

  public Mono<CustomerDto> customerById(Integer id) {
    return repository.findById(id)
        .map(EntityDtoUtil::toDto);
  }

  public Mono<CustomerDto> createCustomer(CustomerDto dto) {
    return Mono.just(dto)
        .map(EntityDtoUtil::toEntity)
        .flatMap(this.repository::save)
        .map(EntityDtoUtil::toDto);
  }

  public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto) {
    return this.repository.findById(id)
        .map(e ->  EntityDtoUtil.toEntity(id, dto))
        .flatMap(this.repository::save)
        .map(EntityDtoUtil::toDto);
  }

  public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
    return this.repository.deleteById(id)
        .thenReturn(DeleteResponseDto.builder().id(id).status(SUCCESS).build())
        .onErrorReturn(DeleteResponseDto.builder().status(FAIL).build());
  }
}
