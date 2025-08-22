package com.gandesc.graphql_play.lec04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  private Integer id;
  private String name;
  private Integer age;
  private String city;
}
