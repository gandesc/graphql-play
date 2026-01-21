package com.gandesc.graphql_play.lec16.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerNotFound implements CustomerResponse {
  private Integer id;
  @Builder.Default
  private String message = "user not found";
}
