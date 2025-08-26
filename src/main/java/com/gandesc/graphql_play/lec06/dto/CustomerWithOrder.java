package com.gandesc.graphql_play.lec06.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWithOrder {
  private Integer id;
  private String name;
  private Integer age;
  private String city;
  private List<CustomerOrderDto> orders;
}