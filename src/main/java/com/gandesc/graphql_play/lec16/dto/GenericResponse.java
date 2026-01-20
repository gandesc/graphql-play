package com.gandesc.graphql_play.lec16.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.graphql.ResponseError;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class GenericResponse<T> {
  private final T data;
  private final List<ResponseError> errors;
  private final boolean dataPresent;

  public GenericResponse(T data) {
    this.data = data;
    this.dataPresent = true;
    this.errors = Collections.EMPTY_LIST;
  }

  public GenericResponse(List<ResponseError> errors) {
    this.data = null;
    this.dataPresent = false;
    this.errors = errors;
  }
}
