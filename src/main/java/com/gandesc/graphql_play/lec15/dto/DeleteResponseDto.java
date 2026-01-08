package com.gandesc.graphql_play.lec15.dto;

import com.gandesc.graphql_play.lec15.dto.Status;
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
