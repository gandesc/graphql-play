package com.gandesc.graphql_play.lec16.serverapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  private Integer id;
  private String name;
  private Integer age;
  private String city;
}
