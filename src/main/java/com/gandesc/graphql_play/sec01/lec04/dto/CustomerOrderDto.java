package com.gandesc.graphql_play.sec01.lec04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDto {
  private UUID id;
  private String description;
}
