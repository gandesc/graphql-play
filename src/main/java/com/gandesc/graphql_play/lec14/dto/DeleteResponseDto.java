package com.gandesc.graphql_play.lec14.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteResponseDto {
  private Integer id;
  private Status status;
}
