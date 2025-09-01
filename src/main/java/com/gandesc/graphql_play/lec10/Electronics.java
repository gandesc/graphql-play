package com.gandesc.graphql_play.lec10;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Electronics {
  @Builder.Default
  private UUID id = UUID.randomUUID();
  private String description;
  private Integer price;
  private String brand;
}
