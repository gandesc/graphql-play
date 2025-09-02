package com.gandesc.graphql_play.lec13.util;

import com.gandesc.graphql_play.lec13.dto.CustomerDto;
import com.gandesc.graphql_play.lec13.entity.Customer;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
  public static Customer toEntity(CustomerDto dto) {
    Customer entity = new Customer();

    BeanUtils.copyProperties(dto, entity);

    return entity;
  }

  public static CustomerDto toEntity(Customer entity) {
    CustomerDto dto = new CustomerDto();

    BeanUtils.copyProperties(entity, dto);

    return dto;
  }
}
