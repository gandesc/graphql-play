package com.gandesc.graphql_play.lec16.serverapp.service;

import com.gandesc.graphql_play.lec16.dto.Action;
import com.gandesc.graphql_play.lec16.dto.CustomerDto;
import com.gandesc.graphql_play.lec16.dto.CustomerEvent;
import com.gandesc.graphql_play.lec16.dto.DeleteResponseDto;
import com.gandesc.graphql_play.lec16.serverapp.repository.CustomerRepository;
import com.gandesc.graphql_play.lec16.serverapp.util.EntityDtoUtil;
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
  private final CustomerEventService eventService;

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
        .map(EntityDtoUtil::toDto)
        .doOnNext(c -> this.eventService.emitEvent(CustomerEvent.builder().id(c.getId())
            .action(Action.CREATED).build()));
  }

  public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto) {
    return this.repository.findById(id)
        .map(e ->  EntityDtoUtil.toEntity(id, dto))
        .flatMap(this.repository::save)
        .map(EntityDtoUtil::toDto)
        .doOnNext(c -> this.eventService.emitEvent(CustomerEvent.builder().id(c.getId())
            .action(Action.UPDATED).build()));
  }

  public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
    return this.repository.deleteById(id)
        .doOnSuccess(c -> this.eventService.emitEvent(CustomerEvent.builder().id(id)
            .action(Action.DELETED).build()))
        .thenReturn(DeleteResponseDto.builder().id(id).status(SUCCESS).build())
        .onErrorReturn(DeleteResponseDto.builder().status(FAIL).build());
  }
}
