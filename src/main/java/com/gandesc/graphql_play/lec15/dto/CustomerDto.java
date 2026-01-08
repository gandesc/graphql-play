package com.gandesc.graphql_play.lec15.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
  private Integer id;
  private String name;
  private Integer age;
  private String city;
}
